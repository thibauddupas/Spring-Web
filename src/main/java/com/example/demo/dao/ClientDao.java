package com.example.demo.dao;

import com.example.demo.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

@Component
public class ClientDao {
    private List<Client> clients = new ArrayList<>();
    //Hashmap à voir

    public ClientDao() {
        clients.add(new Client(1, "Durand", "Marie"));
        clients.add(new Client(2, "Dubois", "Jean"));
        clients.add(new Client(3, "Lefèvre", "Nathalie"));
        clients.add(new Client(4, "Leroy", "Michel"));
        clients.add(new Client(5, "Moreau", "Marie"));
        clients.add(new Client(6, "Roux", "Jean"));
        clients.add(new Client(7, "Fournier", "Jean"));

    }

    public List<Client> listeClientFiltre(String prenomDuClientRecherche) {
        List<Client> listeClientFiltre = clients.stream()
                .filter(p -> prenomDuClientRecherche.equals(p.getPrenom()))
                .collect(Collectors.toList());
        /*for (Client elem: clients) {
            if (elem.getPrenom().equals(prenomDuClientRecherche)){
                listeClientFiltre.add(elem.getNom() + " " + elem.getPrenom() + ", ID=" + elem.getId() + " ; ");
            }
        }*/
        return listeClientFiltre;
    }

    public Client rechercheClient(String nomDuClient) {
        Client clientRechercher = new Client();
        clientRechercher.setNom("XXX");
        for (Client elem : clients){
            if (elem.getNom().equals(nomDuClient)) {
                clientRechercher = elem;
            }
        }
        return clientRechercher;
    }

    public int addClient (String nom, String prenom) {
        long cleLong = currentTimeMillis();
        int cle = (int)cleLong;
        clients.add(new Client(cle, nom, prenom));
        return cle;
    }

    public List <Client> getToutClients(){
        return clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void removeClient(Client client) {
        for (int i=0 ; i < clients.size() ; i++) {
            if (client.getId() == clients.get(i).getId()) {
                clients.remove(i);
            }
        }
    }

    public String getPremierClients() {
        Client clientEnPremier = clients.get(0);
        return clientEnPremier.getNom();
    }
}
