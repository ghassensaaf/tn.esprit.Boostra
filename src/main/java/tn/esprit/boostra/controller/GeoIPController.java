package tn.esprit.boostra.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.boostra.entity.GeoIP;
import tn.esprit.boostra.service.GeoIPLocationService;
import java.net.InetAddress;


@RestController
public class GeoIPController {

	private final GeoIPLocationService geoIPLocationService;

    public GeoIPController(GeoIPLocationService geoIPLocationService) {
        this.geoIPLocationService = geoIPLocationService;
    }

    @GetMapping("/geoIP")
    public GeoIP getLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {
    	
        return geoIPLocationService.getIpLocation(request);
    }
}
