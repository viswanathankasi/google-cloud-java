/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class IdentityTest {

  private static final Identity ALL_USERS = Identity.allUsers();
  private static final Identity ALL_AUTH_USERS = Identity.allAuthenticatedUsers();
  private static final Identity USER = Identity.user("abc@gmail.com");
  private static final Identity SERVICE_ACCOUNT =
      Identity.serviceAccount("service-account@gmail.com");
  private static final Identity GROUP = Identity.group("group@gmail.com");
  private static final Identity DOMAIN = Identity.domain("google.com");

  @Test
  public void testAllUsers() {
    assertEquals(Identity.Type.ALL_USERS, ALL_USERS.getType());
    assertNull(ALL_USERS.getValue());
  }

  @Test
  public void testAllUsersDeprecated() {
    assertEquals(Identity.Type.ALL_USERS, ALL_USERS.type());
    assertNull(ALL_USERS.value());
  }

  @Test
  public void testAllAuthenticatedUsers() {
    assertEquals(Identity.Type.ALL_AUTHENTICATED_USERS, ALL_AUTH_USERS.getType());
    assertNull(ALL_AUTH_USERS.getValue());
  }

  @Test
  public void testAllAuthenticatedUsersDeprecated() {
    assertEquals(Identity.Type.ALL_AUTHENTICATED_USERS, ALL_AUTH_USERS.type());
    assertNull(ALL_AUTH_USERS.value());
  }

  @Test
  public void testUser() {
    assertEquals(Identity.Type.USER, USER.getType());
    assertEquals("abc@gmail.com", USER.getValue());
  }

  @Test
  public void testUserDeprecated() {
    assertEquals(Identity.Type.USER, USER.type());
    assertEquals("abc@gmail.com", USER.value());
  }

  @Test(expected = NullPointerException.class)
  public void testUserNullEmail() {
    Identity.user(null);
  }

  @Test
  public void testServiceAccount() {
    assertEquals(Identity.Type.SERVICE_ACCOUNT, SERVICE_ACCOUNT.getType());
    assertEquals("service-account@gmail.com", SERVICE_ACCOUNT.getValue());
  }

  @Test
  public void testServiceAccountDeprecated() {
    assertEquals(Identity.Type.SERVICE_ACCOUNT, SERVICE_ACCOUNT.type());
    assertEquals("service-account@gmail.com", SERVICE_ACCOUNT.value());
  }

  @Test(expected = NullPointerException.class)
  public void testServiceAccountNullEmail() {
    Identity.serviceAccount(null);
  }

  @Test
  public void testGroup() {
    assertEquals(Identity.Type.GROUP, GROUP.getType());
    assertEquals("group@gmail.com", GROUP.getValue());
  }

  @Test
  public void testGroupDeprecated() {
    assertEquals(Identity.Type.GROUP, GROUP.type());
    assertEquals("group@gmail.com", GROUP.value());
  }

  @Test(expected = NullPointerException.class)
  public void testGroupNullEmail() {
    Identity.group(null);
  }

  @Test
  public void testDomain() {
    assertEquals(Identity.Type.DOMAIN, DOMAIN.getType());
    assertEquals("google.com", DOMAIN.getValue());
  }

  @Test
  public void testDomainDeprecated() {
    assertEquals(Identity.Type.DOMAIN, DOMAIN.type());
    assertEquals("google.com", DOMAIN.value());
  }

  @Test(expected = NullPointerException.class)
  public void testDomainNullId() {
    Identity.domain(null);
  }

  @Test
  public void testIdentityToAndFromPb() {
    compareIdentities(ALL_USERS, Identity.valueOf(ALL_USERS.strValue()));
    compareIdentities(ALL_AUTH_USERS, Identity.valueOf(ALL_AUTH_USERS.strValue()));
    compareIdentities(USER, Identity.valueOf(USER.strValue()));
    compareIdentities(SERVICE_ACCOUNT, Identity.valueOf(SERVICE_ACCOUNT.strValue()));
    compareIdentities(GROUP, Identity.valueOf(GROUP.strValue()));
    compareIdentities(DOMAIN, Identity.valueOf(DOMAIN.strValue()));
  }

  private void compareIdentities(Identity expected, Identity actual) {
    assertEquals(expected, actual);
    assertEquals(expected.getType(), actual.getType());
    assertEquals(expected.getValue(), actual.getValue());
  }
}
