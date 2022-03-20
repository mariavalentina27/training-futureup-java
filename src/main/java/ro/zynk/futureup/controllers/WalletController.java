package ro.zynk.futureup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping()
    public ResponseEntity<CoinResponse> saveNewCoin(@RequestBody  CoinResponse coinResponse){
        return new ResponseEntity<CoinResponse>(walletService.saveNewCoin(coinResponse), HttpStatus.OK);
    }
}
