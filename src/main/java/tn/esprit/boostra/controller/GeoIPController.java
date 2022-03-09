package tn.esprit.boostra.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import java.io.IOException;
import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.boostra.entity.GeoIP;
import tn.esprit.boostra.service.GeoIPLocationService;

@RestController
public class GeoIPController {

  private final GeoIPLocationService geoIPLocationService;

  public GeoIPController(GeoIPLocationService geoIPLocationService) {
    this.geoIPLocationService = geoIPLocationService;
  }

  @GetMapping("/geoIP")
  public GeoIP getLocation(HttpServletRequest request)
      throws IOException, GeoIp2Exception {

    return geoIPLocationService.getIpLocation(request);
  }
}
