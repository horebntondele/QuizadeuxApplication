package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.PricingDao;
import com.Project.QuizadeuxApplication.Entities.Pricing;
import com.Project.QuizadeuxApplication.Service.PricingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PricingServiceImpl implements PricingService {

    private PricingDao pricingDao;

    public PricingServiceImpl(PricingDao pricingDao) {
        this.pricingDao = pricingDao;
    }

    @Override
    public Pricing NewService(Pricing pricing) {
        return pricingDao.save(pricing);
    }
}
