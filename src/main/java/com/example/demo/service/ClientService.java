package com.example.demo.service;

import com.example.demo.dao.ClientDao;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    public List<Client> getClients() {
        return clientDao.getClients();
    };

    public String getPremierClients() {
        return clientDao.getPremierClients();
    }

    public List<Client> getToutClients(){
        return clientDao.getToutClients();
    }

    public Client rechercheClient(String nomDuClient) {
        return clientDao.rechercheClient(nomDuClient);
    }

    public int addClient(String nom, String prenom) {
        return clientDao.addClient(nom,prenom);
    }

    public void removeClient(Client client) {
        clientDao.removeClient(client);
    }

    public List<Client> listeClientFiltre(String prenomDuClientRecherche) {
        return clientDao.listeClientFiltre(prenomDuClientRecherche);
    }
}
