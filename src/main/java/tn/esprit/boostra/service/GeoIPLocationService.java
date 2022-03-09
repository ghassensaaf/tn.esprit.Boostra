package tn.esprit.boostra.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import tn.esprit.boostra.entity.GeoIP;

public interface GeoIPLocationService {
  GeoIP getIpLocation(HttpServletRequest request)
      throws IOException, GeoIp2Exception;
}