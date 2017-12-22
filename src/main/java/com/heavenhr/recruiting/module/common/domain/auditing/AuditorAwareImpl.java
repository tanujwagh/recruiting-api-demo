package com.heavenhr.recruiting.module.common.domain.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (NullPointerException ex){}
        return "ANONYMOUS";
    }
}