package org.owpk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.owpk.entities.Item;
import org.owpk.services.ItemService;
import org.owpk.utils.CartManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderTest extends AbstractTest {

    @MockBean
    private ItemService itemService;

    @Test
    public void getAllOrdersForSpecificUser() throws Exception {
        mockMvc.perform(get(baseCtxPath + "/order/all/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void tryToAddItemToCart() throws Exception {
        when(itemService.findById(1L)).thenReturn(new Item(1L, "name", 5));
        MvcResult result = mockMvc.perform(get(baseCtxPath + "/order/cart/add/1"))
                .andExpect(status().isOk())
                .andReturn();
        var req = result.getRequest().getSession().getAttribute("scopedTarget.cartManager");
        CartManager cartManager = (CartManager) req;

        Assertions.assertEquals(cartManager.getItemList().size(), 1);
        Assertions.assertEquals(cartManager.getTotalCost(), 5);
    }
}
