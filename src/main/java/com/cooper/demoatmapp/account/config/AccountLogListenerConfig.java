package com.cooper.demoatmapp.account.config;

import com.cooper.demoatmapp.account.listener.AccountHistoryListener;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class AccountLogListenerConfig implements InitializingBean {

    private final EntityManagerFactory entityManagerFactory;

    private final AccountHistoryListener accountHistoryListener;

    @Override
    public void afterPropertiesSet() {
        SessionFactoryImpl sessionFactoryImpl = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry eventListenerRegistry
                = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);

        eventListenerRegistry.appendListeners(EventType.POST_COMMIT_INSERT, accountHistoryListener);
        eventListenerRegistry.appendListeners(EventType.POST_COMMIT_UPDATE, accountHistoryListener);
    }

}
