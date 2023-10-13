package com.bancobase.bootcamp.controller;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.services.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
@Tag(name = "Currency controller")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Operation(summary = "Get currency exchanges from today")
    @GetMapping
    public ResponseEntity<List<CurrencyDTO>> getAccountByAccountNumber() {
        List<CurrencyDTO> currencies = this.currencyService.getCurrencies();

        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
}
