package org.gepron1x.auth.api;

public interface DualFactorService {
 void sendVerificationCode(AuthProfile profile, String code);
}
