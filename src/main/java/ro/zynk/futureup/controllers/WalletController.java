package ro.zynk.futureup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.zynk.futureup.controllers.requests.CoinTransactionRequest;
import ro.zynk.futureup.controllers.responses.CoinTransactionResponse;
import ro.zynk.futureup.controllers.responses.WalletResponse;
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

    @PostMapping
    public ResponseEntity<WalletResponse> saveWallet(@RequestBody WalletResponse walletResponse) {
        return new ResponseEntity<>(walletService.saveNewWallet(walletResponse), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WalletResponse> getWalletById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(walletService.getWallet(id), HttpStatus.OK);
    }

    @PutMapping(value = "/buy_coin")
    public ResponseEntity<CoinTransactionResponse> buyCoin(@RequestBody CoinTransactionRequest buyCoinRequest) {
        return new ResponseEntity<>(walletService.buyCoin(buyCoinRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/get_all_owned_coins/{walletId}")
    public ResponseEntity<List<CoinTransactionResponse>> getAllOwnedCoinsFromWallet(@PathVariable("walletId") Long walletId) {
        return new ResponseEntity<>(walletService.getAllCoinsFromWallet(walletId), HttpStatus.OK);

    }
}
