package com.aethelgard.archive.presentation.rest;

import com.aethelgard.archive.domain.model.AncientSpell;
import com.aethelgard.archive.domain.port.in.FindSpellsUseCase;
import com.aethelgard.archive.domain.port.in.InvokeCatalystUseCase;
import com.aethelgard.archive.domain.port.in.ReadScrollUseCase;
import com.aethelgard.archive.presentation.rest.dto.ModernSpellDTO;
import com.aethelgard.archive.presentation.rest.mapper.SpellDtoMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ArchiveController.class)
class ArchiveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindSpellsUseCase findSpellsUseCase;

    @MockBean
    private InvokeCatalystUseCase invokeCatalystUseCase;

    @MockBean
    private ReadScrollUseCase readScrollUseCase;

    @MockBean
    private SpellDtoMapper spellDtoMapper;

    @Test
    void shouldInvokeCatalyst() throws Exception {
        Mockito.when(invokeCatalystUseCase.invoke("FIRE")).thenReturn("Fire Catalyst Invoked");

        mockMvc.perform(post("/api/catalysts/FIRE"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fire Catalyst Invoked"));
    }

    @Test
    void shouldReturnLegacyScroll() throws Exception {
        Mockito.when(readScrollUseCase.readLegacyScroll()).thenReturn("Deciphered Scroll Data");

        mockMvc.perform(get("/api/scrolls/legacy"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deciphered Scroll Data"));
    }

    @Test
    void shouldReturnForbiddenSpells() throws Exception {
        AncientSpell spell = new AncientSpell();
        spell.setName("Dark Magic");
        spell.setDangerLevel(10);
        
        ModernSpellDTO dto = new ModernSpellDTO();
        dto.setName("Dark Magic");

        Mockito.when(findSpellsUseCase.findForbiddenSpells(anyInt(), anyString())).thenReturn(List.of(spell));
        Mockito.when(spellDtoMapper.toDtoList(Mockito.anyList())).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/spells/forbidden?minLevel=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dark Magic"));
    }
}
