package com.example.demo.api;

import com.example.demo.formulaire.ActionClientForm;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.ClientService;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String getRacine(final Model mode) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/recherche/{nomDuClientConnecte}")
    public String getClientParNom(@PathVariable String nomDuClientConnecte, final Model model) {
        String aAfficher;
        Client clientRecherche = clientService.rechercheClient(nomDuClientConnecte);
        if (clientRecherche.getNom().equals("XXX")) {
            aAfficher = "Client : inconnu";
        } else {
            aAfficher = "Client : " + clientRecherche.getNom() + " " + clientRecherche.getPrenom() + " (Id=" + clientRecherche.getId() + ")";
        }
        model.addAttribute("listeToutClients", clientRecherche);
        return "clients";
    }

    @GetMapping("/remove/{nomDuClientRemove}")
    public String removeClient(@PathVariable String nomDuClientRemove, final Model model) {
        Client clientRecherche = clientService.rechercheClient(nomDuClientRemove);
        if (!clientRecherche.getNom().equals("XXX")) {
            clientService.removeClient(clientRecherche);
        }
        List<Client> listeToutClients = clientService.getToutClients();
        model.addAttribute("listeToutClients", listeToutClients);
        return "clients";
    }

    @GetMapping("/filtre/{prenomDuClientRecherche}")
    public String filtreClient(@PathVariable String prenomDuClientRecherche, final Model model) {
        List<Client> listClientFiltre = clientService.listeClientFiltre(prenomDuClientRecherche);
        model.addAttribute("listeToutClients", listClientFiltre);
        return "clients";
    }


    @GetMapping("/clients")
    public String getClients(final Model model) {
        List<Client> listeToutClients = clientService.getToutClients();
        model.addAttribute("listeToutClients", listeToutClients);
        return "clients";
    }

    @RequestMapping(value = "{clientsAjout}", method =  {RequestMethod.POST, RequestMethod.GET})
    public String getClientsAjout(Model model, @ModelAttribute("actionClientForm") ActionClientForm actionClientForm) {

        String nom = actionClientForm.getNom();
        String prenom = actionClientForm.getPrenom();
        String errorMessage = "";
        if (nom != null && nom.length() > 0 //
                && prenom != null && prenom.length() > 0) {
            Client clientRecherche = clientService.rechercheClient(nom);
            if (clientRecherche.getNom().equals("XXX")) {
                int cle = clientService.addClient(nom, prenom);
                return "redirect:/clients";
            } else {
                errorMessage = "Client déja connu";
            }
        } else {
            errorMessage = "Merci de renseigner un nom et un prénom";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "clientsAjout";
    }
}