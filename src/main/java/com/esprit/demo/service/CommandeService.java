package com.esprit.demo.service;

import com.esprit.demo.dto.CommandeDatesDTO;
import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.entity.Menu;
import com.esprit.demo.repository.ClientRepository;
import com.esprit.demo.repository.CommandeRepository;
import com.esprit.demo.repository.MenuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CommandeService implements ICommandeService {
    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MenuRepository menuRepository;

    public List<Commande> retrieveAllClients(Long id) {
        return commandeRepository.findAllByClientIdClient(id);
    }

    @Override
    public List<Commande> retrieveAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande addCommande(Commande e) {
        return commandeRepository.save(e);
    }

    @Override
    public Commande updateCommande(Commande e) {
        return commandeRepository.save(e);
    }

    @Override
    public Commande retrieveCommande(Long idCommande) {
        return commandeRepository.findById(idCommande)
                .orElse(null);
    }

    @Override
    public void removeCommande(Long idCommande) {
        commandeRepository.deleteById(idCommande);
    }

    @Override
    public List<Commande> addCommandes(List<Commande> Commandes) {
        return commandeRepository.saveAll(Commandes);
    }

    @Override
    public List<Commande> findAllByIdClientAndDateCommandeBetween(Long idClient, CommandeDatesDTO dto) {
        return commandeRepository.findAllByClientIdClientAndDateCommandeBetween(idClient, dto.getDate1(), dto.getDate2());
    }

    @Override
    public List<Commande> findAllByDateCommandeBetweenOrderByNoteAsc(CommandeDatesDTO dto) {
        return commandeRepository.findAllByDateCommandeBetweenOrderByNoteAsc(dto.getDate1(), dto.getDate2());
    }

    @Override
    public void ajouterCommandeEtAffecterAClientEtMenu(Commande commande,
                                                       String identifiant,
                                                       String libelleMenu) {
        Client client = clientRepository.findByIdentifiant(identifiant)
                .orElseThrow(() -> new IllegalArgumentException("Client not found."));

        Menu menu = menuRepository.findByLibelleMenu(libelleMenu)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found."));

        commande.setClient(client);
        commande.setMenu(menu);

        Float prixTotal = menu.getPrixTotal() == null ? 0f : menu.getPrixTotal();
        Float totalRemise = prixTotal * commande.getPourcentageRemise() / 100;
        Float totalCommande = prixTotal - totalRemise;

        commande.setTotalRemise(totalRemise);
        commande.setTotalCommande(totalCommande);

        commandeRepository.save(commande);
    }

    @Scheduled(fixedDelay = 10000) // 10s
    @Override
    public void findCurrentYearCommandesOrderByNote() {
        int year = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        List<Commande> commandes = commandeRepository.findAllByDateCommandeBetweenOrderByNoteAsc(startDate, endDate);
        commandes.forEach(commande -> {
            log.info("La commande faite le {} d'un montant global de {} a une note de {}", commande.getDateCommande(), commande.getTotalCommande(), commande.getNote());
        });
    }

    @Override
    @Scheduled(fixedDelay = 10000)
    public void menuPlusCommande() {
        int nbCommandesMax = 0;
        List<Menu> allMenus = menuRepository.findAll();
        Menu bestMenu = allMenus.get(0);
        for (Menu m : allMenus) {
            int nbCommandesMenu = m.getCommandes().size();
            if (nbCommandesMenu > nbCommandesMax) {
                nbCommandesMax = nbCommandesMenu;
                bestMenu = m;
            }
        }
        log.info("Le menu le plus commandé dans notre restaurant est {} commandé {} fois", bestMenu.getLibelleMenu(), nbCommandesMax);
    }
}
