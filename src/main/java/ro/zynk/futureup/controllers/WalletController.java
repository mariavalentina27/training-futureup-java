package ro.zynk.futureup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.zynk.futureup.controllers.responses.CoinResponse;
import ro.zynk.futureup.services.WalletService;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping(value = "/all_coins")
    public List<CoinResponse> getAllCoins(){
        return walletService.getAllCoins();
    }
}
