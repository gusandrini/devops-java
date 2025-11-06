package br.com.fiap.projeto_mottu.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import br.com.fiap.projeto_mottu.model.Pais;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class PaisRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaisRepository paisRepository;

    private Pais pais;

    @BeforeEach
    void setUp() {
        pais = new Pais();
        pais.setNm_pais("Pais Teste");
    }

    @Test
    void testCreate() {
        // Act
        Pais saved = paisRepository.save(pais);

        // Assert
        assertNotNull(saved.getId_pais());
        assertEquals("Pais Teste", saved.getNm_pais());
    }
    @Test
    void testRead() {
        // Arrange
        Pais saved = entityManager.persistAndFlush(pais);

        // Act
        Optional<Pais> found = paisRepository.findById(saved.getId_pais());

        // Assert
        assertTrue(found.isPresent());
        assertEquals(saved.getId_pais(), found.get().getId_pais());
        assertEquals("Pais Teste", found.get().getNm_pais());
    }

    @Test
    void testReadAll() {
        // Arrange
        entityManager.persistAndFlush(pais);

        Pais pais2 = new Pais();
        pais2.setNm_pais("Pais Principal");
        entityManager.persistAndFlush(pais2);

        // Act
        List<Pais> all = paisRepository.findAll();

        // Assert
        assertTrue(all.size() >= 2);
    }

    @Test
    void testUpdate() {
        // Arrange
        Pais saved = entityManager.persistAndFlush(pais);
        Long id = saved.getId_pais();

        // Act
        saved.setNm_pais("Pais Atualizada");
        Pais updated = paisRepository.save(saved);

        // Assert
        assertEquals(id, updated.getId_pais());
        assertEquals("Pais Atualizada", updated.getNm_pais());
    }

    @Test
    void testDelete() {
        // Arrange
        Pais saved = entityManager.persistAndFlush(pais);
        Long id = saved.getId_pais();

        // Act
        paisRepository.deleteById(id);

        // Assert
        Optional<Pais> deleted = paisRepository.findById(id);
        assertFalse(deleted.isPresent());
    }

    @Test
    void testFindByIdNotFound() {
        // Act
        Optional<Pais> found = paisRepository.findById(999L);

        // Assert
        assertFalse(found.isPresent());
    }



}