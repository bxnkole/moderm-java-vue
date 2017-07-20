package com.banks.modernjavavue.service;

import com.banks.modernjavavue.model.QuoteEntity;
import com.banks.modernjavavue.repos.QuoteRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

/**
 * Created on 2016-01-18.
 *
 * @author banks
 */
@Service
public class QuoteService {

    private QuoteRepo quoteRepo;

    public QuoteService(QuoteRepo quoteRepo) {
        this.quoteRepo = quoteRepo;
    }

    @Transactional
    public QuoteEntity getRandomQuote() {
        long totalCount = getTotalCount();
        Random random = new Random();
        List<QuoteEntity> all = quoteRepo.findAll();
        QuoteEntity quoteEntity = all.get(random.nextInt((int) totalCount));
        return quoteEntity;
    }

    public long getTotalCount() {
        return quoteRepo.count();
    }

    public void create(List<QuoteEntity> collect) {
        quoteRepo.save(collect);
    }
}
