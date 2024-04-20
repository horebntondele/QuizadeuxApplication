package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.PaymentDao;
import com.Project.QuizadeuxApplication.Entities.Payment;
import com.Project.QuizadeuxApplication.Service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Payment NewPayment(Payment payment) {
        return paymentDao.save(payment);
    }
}
