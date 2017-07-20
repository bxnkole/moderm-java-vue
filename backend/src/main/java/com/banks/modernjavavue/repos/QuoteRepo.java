package com.banks.modernjavavue.repos;

import com.banks.modernjavavue.model.QuoteEntity;
import com.banks.modernjavavue.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by banks on 019 Jul 19 2017 8:41 PM.
 */
@Repository
public interface QuoteRepo extends JpaRepository<QuoteEntity, Long> {
}
