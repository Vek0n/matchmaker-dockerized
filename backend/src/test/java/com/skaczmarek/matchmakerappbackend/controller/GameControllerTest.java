package com.skaczmarek.matchmakerappbackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@RunWith(SpringRunner.class)
//@WebMvcTest(GameController.class)
//@ComponentScan(basePackages = {"com.skaczmarek.matchmakerappbackend"})
//public class GameControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private GameService gameServiceMock;
//
//    @Test
//    public void shouldGetGame() throws Exception {
//        Game givenGame = new GameBuilder()
//                .defaultGame()
//                .build();
//
//        given(gameServiceMock.getGame(givenGame.getId())).willReturn(givenGame);
//
//        mvc.perform(get("/games/" + givenGame.getId())
//        .contentType(MediaType.ALL))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(givenGame.getId()))
//                .andExpect(jsonPath("$.gameName").value(givenGame.getGameName()));
//    }
//}
