package ro.zynk.futureup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<CoinResponse> getAllCoins(){
        return coinService.getAllCoins();
    }
}
