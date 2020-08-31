package com.demo.service;

import com.demo.model.Cjenovnik;
import com.demo.model.Racun;
import com.demo.model.User;
import com.demo.repository.RacunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class RacunService {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private RacunRepository racunRepository;

    public void createBill(double kilometrage, Cjenovnik priceList, Long user_id) {
        Racun bill = new Racun();
        bill.setPaid(false);
        User user = this.userService.getUserById(user_id);
        bill.setUser(user);

        double price = kilometrage * priceList.getCijenaPoKM();
        bill.setCijena(price);
        this.userDetailsService.rentPrivilege(false, user_id);

        this.racunRepository.save(bill);
    }
}
