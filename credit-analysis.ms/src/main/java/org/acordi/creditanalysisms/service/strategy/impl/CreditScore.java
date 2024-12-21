package org.acordi.creditanalysisms.service.strategy.impl;

import org.acordi.creditanalysisms.constants.ConstantMessage;
import org.acordi.creditanalysisms.exceptions.StrategyException;
import org.acordi.creditanalysisms.service.strategy.CreditComputation;
import org.acordi.creditanalysisms.domain.Proposal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;
@Order(2)
@Component
public class CreditScore implements CreditComputation {

    @Override
    public int compute(Proposal proposal) {
        //I actually wanted to use an API (like Serasa) to consult the user's
        //credit score.
        //It is paid, though, so let's just randomize it.
        int score = score();

        if(score <= 200){
            throw new StrategyException(String.format(ConstantMessage.LOW_SCORE, proposal.getUser().getName()));
        }
        if (score <= 400) {
            return 150;
        }
        if (score <= 600){
            return 200;
        }
        return 240;
    }

    private int score(){
        return new Random().nextInt(0, 1000);
    }
}
