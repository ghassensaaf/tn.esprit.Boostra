package tn.esprit.boostra.service;


import com.maxmind.geoip2.exception.GeoIp2Exception;

import tn.esprit.boostra.entity.GeoIP;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GeoIPLocationService {
    GeoIP getIpLocation(HttpServletRequest request) throws IOException, GeoIp2Exception;
    
}