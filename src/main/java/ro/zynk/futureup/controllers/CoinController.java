package ro.zynk.futureup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.zynk.futureup.controllers.responses.CoinResponse;
import ro.zynk.futureup.services.CoinService;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {
    private CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping(value = "/all_coins")
    public List<CoinResponse> getAllCoins() {
        return coinService.getAllCoins();
    }

    @PostMapping
    public ResponseEntity<CoinResponse> saveNewCoin(@RequestBody CoinResponse coinResponse) {
        return new ResponseEntity<>(coinService.saveNewCoin(coinResponse), HttpStatus.OK);
    }
}
