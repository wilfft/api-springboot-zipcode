package com.wine.api.Service;

import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public boolean ValidateZipcode(Integer inicio, Integer fim) {

	if (inicio < fim && inicio > 0 && fim <= 99999999) {
	    return true;
	} else
	    return false;
    }
}
