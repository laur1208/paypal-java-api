package com.rlaur.paypal;

import com.rlaur.paypal.http.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.json.JsonObject;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given we have a generated token")
class TokenTest {

    @Mock
    private static Resource resource;

    @InjectMocks
    private Token token;

    @Test
    @DisplayName("Then the token should have a scope")
    void scope() {
//        final JsonObject jsonObject = mock(JsonObject.class);
//        when(resource.json()).thenReturn(jsonObject);
//        when(resource.json().getString("scope")).thenReturn("1");
//        assertThat(token.scope()).isEqualTo("1");
    }

    void value() {
    }

    void type() {
    }

    void appId() {
    }

    void expiresIn() {
    }

    void nonce() {
    }
}