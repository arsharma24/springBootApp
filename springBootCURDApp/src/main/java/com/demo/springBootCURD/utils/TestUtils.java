package com.demo.springBootCURD.utils;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.domain.Organisation;

public class TestUtils {

	public static Dealer getDealer(){
        Organisation organisation = new Organisation("OR123", "OR_ABUS", "US_ORG");
        return new Dealer("XABUS12345", "XABUS", organisation);
    }
}
