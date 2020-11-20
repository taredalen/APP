import java.util.*;
//----------------------------------------------------------------------------------------------------------------------
class Etudiant {
    public String nom, prenom, niveau;
    public double moyenne;
    public ArrayList<Matiere> matieres;

    public Etudiant(String niveau, String nom, String prenom, double moyenne, ArrayList<Matiere> matieres) {
        this.niveau = niveau;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.matieres = matieres;
    }

    public void afficherEtudiant() {
        System.out.println("⎥ NIVEAU     ⎥ " + niveau + "\n⎥ NOM        ⎥ " + nom + "\n⎥ PRENOM     ⎥ " + prenom + "\n⎥ MOYENNE    ⎥ " + moyenne);
    }

    public double moyenneEtudiant(int key) {
        double moyenneOrale = Matiere.moyenneOrale(key);
        double moyenneEcrite = Matiere.moyenneEcrite(key);
        return moyenne = (moyenneOrale + moyenneEcrite) / 2;
    }

    public static void supprimerEtudiant(Etudiant etudiant, int key) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEntrez 1 pour supprimer " + etudiant.getNom() + " " + etudiant.getPrenom() + "\nEntrez 0 pour annuler la suppression : ");
        int reponse = Integer.parseInt(sc.next());
        if (reponse == 1) {
            APP4.hashMapEtudiants.remove(key);
            System.out.println("Etudiant supprime de la liste.");
            return;
        }if (reponse == 0) System.out.println("Suppression annulee.");
    }

    public static void listEtudiants() {
        for (Integer key : APP4.hashMapEtudiants.keySet()) {
            System.out.println("N° : " + key + "⎥  Nom : " + APP4.hashMapEtudiants.get(key).getNom() + ", Prenom : " + APP4.hashMapEtudiants.get(key).getPrenom() + "  " + APP4.hashMapEtudiants.get(key).getNiveau());
        }
    }

    public static void listeValidationAnnee() {
        int nombreEtudiants = 0;
        for (Integer key : APP4.hashMapEtudiants.keySet()) {
            if (APP4.hashMapEtudiants.get(key).getMoyenne() >= 10) {
                nombreEtudiants++;
            }
        }
        System.out.println("Nombre d'etudiants qui valident l'annee    : " + nombreEtudiants);
        System.out.println("Nombre de ceux qui ne valident pas l'annee : " + (APP4.hashMapEtudiants.size() - nombreEtudiants));
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom;  }
    public String getNiveau() {  return niveau; }
    public double getMoyenne() { return moyenne; }
    public ArrayList<Matiere> getMatieres() { return matieres; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
//----------------------------------------------------------------------------------------------------------------------
class EtudiantDerniere extends Etudiant {
    public double noteProjet;

    public EtudiantDerniere(String niveau, String nom, String prenom, double moyenne, ArrayList<Matiere> matieres, double noteProjet) {
        super(niveau, nom, prenom, moyenne, matieres);
        this.noteProjet = noteProjet;
    }

    public void setNoteProjet(double noteProjet) { this.noteProjet = noteProjet; }

    @Override
    public void afficherEtudiant() {
        super.afficherEtudiant();
        System.out.println("⎥ PROJET     ⎥ " + noteProjet);
    }
    @Override
    public double moyenneEtudiant(int key) {
        return moyenne = (super.moyenneEtudiant(key) * 2 + noteProjet) / 3;
    }
}
//---------------------------------------------------------------------------------------------------------------------
class Matiere {

    public double noteOrale, notEcrite;
    public String nomMatiere;
    // APP4.hashMapEtudiants.get(key).getMatieres() = liste contenant matieres
    // APP4.hashMapEtudiants.get(key).getMatieres().size() = taille de la liste des matieres

    public Matiere(double notEcrite, double noteOrale, String nomMatiere) {
        this.nomMatiere = nomMatiere;
        this.notEcrite = notEcrite;
        this.noteOrale = noteOrale;
    }

    public static ArrayList<Matiere> remplirNotes(String[] liste) {
        while (true) {
            try {
                ArrayList<Matiere> list = new ArrayList<>();
                double noteOrale, notEcrite;
                Scanner sc = new Scanner(System.in);
                for (String nomMatiere : liste) {
                    if(nomMatiere.equals("Projet")) continue; // on passe puisqu'on recupere dans la methode nouveauEtudiant
                     System.out.print("La NE obtenue en " + nomMatiere + " : ");
                     while ((notEcrite = sc.nextDouble()) < 0 || notEcrite > 20) {
                         System.out.println("Entrez une note entre 0 et 20 ! ");
                         System.out.print("La NE obtenue en " + nomMatiere + " : ");
                     }
                     System.out.print("La NO obtenue en " + nomMatiere + " : ");
                     while ((noteOrale = sc.nextDouble()) < 0 || noteOrale > 20) {
                         System.out.println("Entrez une note entre 0 et 20 ! ");
                         System.out.print("La NO obtenue en " + nomMatiere + " : ");
                     }
                     Matiere matiere = new Matiere(notEcrite, noteOrale, nomMatiere); // creation d'objet matiere
                     list.add(matiere); // ajout de cet objet dans une liste
                } return list;
            } catch (InputMismatchException e) { System.out.println("Note doit correspondre a un chiffre!Recommencez!"); }
        }
    }

    public static double moyenneOrale(int key) {
        double sum = 0;
        for (Matiere m : APP4.hashMapEtudiants.get(key).getMatieres()) { // recherche dans la liste
            sum += m.getNoteOrale() * 0.4;
        } return sum / (APP4.hashMapEtudiants.get(key).getMatieres().size() * 0.4); // taille de la liste
    }

    public static double moyenneEcrite(int key) {
        double sum = 0;
        for (Matiere m : APP4.hashMapEtudiants.get(key).getMatieres()) {
            sum += m.getNotEcrite() * 0.6;
        } return sum / (APP4.hashMapEtudiants.get(key).getMatieres().size() * 0.6);
    }

    public static void afficherMatiere(int key) {
        for (Matiere m : APP4.hashMapEtudiants.get(key).getMatieres())
            System.out.println("⎥ " + m.getNomMatiere() + " : NE = " + m.getNotEcrite() + " , NO = " + m.getNoteOrale());
    }

    public String getNomMatiere() { return nomMatiere; }
    public double getNotEcrite() { return notEcrite; }
    public double getNoteOrale() { return noteOrale; }

    public void setNotEcrite(double notEcrite) { this.notEcrite = notEcrite; }
    public void setNoteOrale(double noteOrale) { this.noteOrale = noteOrale; }

}
//----------------------------------------------------------------------------------------------------------------------
public class APP4 {
    public static HashMap<Integer, Etudiant> hashMapEtudiants = new HashMap<>();

    public static Scanner sc = new Scanner(System.in);
    public static int key, keyInit = 0;

    static public String[] listeMatieresINGE1 = {"Math", "Physi", "Program", "Electro"};
    static public String[] listeMatieresINGE2 = {"Python", "Math", "Anglais", "BDD"};
    static public String[] listeMatieresINGE3 = {"Robotique","IA", "ML", "Projet"};

    public static void main(String[] args) {
        String menu =
                    "-----------------------------------------------  " +
                    "\n0 ⎢MENU ⎢   ADMINISTRATION SCOLAIRE  ⎥ EXIT ⎥ 9  " +
                    "\n-----------------------------------------------  " +
                    "\n1 ⎢ AJOUTER ETUDIANT   ⎢⎥ STAT D'ADMISSION  ⎥ 8  " +
                    "\n2 ⎢ AFFICHER ETUDIANT  ⎢⎥ LISTE D'ETUDIANTS ⎥ 7  " +
                    "\n3 ⎢ MODIFIER ETUDIANT  ⎢⎥ ADMISSION ETUDIANT⎥ 6  " +
                    "\n4 ⎢ SUPPRIMER ETUDIANT ⎢⎥ MOYENNE ETUDIANT  ⎥ 5  " +
                    "\n-----------------------------------------------\n" ;
        System.out.print(menu);
        System.out.print("Entrez l'option souhaitee apres ce symbole : ");
        while (true) {
            try {
                System.out.print("➤ ");
                switch (Integer.parseInt(sc.next())) {
                    case 0:
                        System.out.print(menu);
                        break;
                    case 1:
                        System.out.print("\n--------------- NOUVEAU ETUDIANT --------------\n");
                        nouveauEtudiant();
                        System.out.println("--------- ETUDIANT ENREGISTRE SOUS N°" + keyInit + " --------\n");
                        break;
                    case 2:
                        System.out.print("Entrez le numero d'etudiant : ");
                        key = sc.nextInt(); // conatains verifie l'existance de la cle dans hashmap
                        if (!hashMapEtudiants.containsKey(key)) System.out.println("Le numero est incorrect!");
                        else {
                            System.out.println("-------------- AFFICHER ETUDIANT --------------");
                            hashMapEtudiants.get(key).afficherEtudiant(); // hashMapEtudiants.get(key) = objet(etudiant)
                            System.out.println("-----------------------------------------------");
                            Matiere.afficherMatiere(key);
                            System.out.println("-----------------------------------------------\n");
                        }
                        break;
                    case 3:
                        System.out.print("Entrez le numero d'etudiant : ");
                        key = sc.nextInt();
                        if(!hashMapEtudiants.containsKey(key)) System.out.println("Le numero est incorrect!");
                        else modifierEtudiant(key);
                        hashMapEtudiants.get(key).moyenneEtudiant(key);
                        break;
                    case 4:
                        System.out.println("\n------------ SUPPRESSION ETUDIANT -------------");
                        System.out.print("Entrez le numero d'etudiant : ");
                        key = sc.nextInt();
                        Etudiant etudiant = APP4.hashMapEtudiants.get(key);
                        if(!hashMapEtudiants.containsKey(key)) System.out.println("Le numero est incorrect!");
                        else Etudiant.supprimerEtudiant(etudiant, key);
                        System.out.println("-----------------------------------------------\n");
                        break;
                    case 5:
                        System.out.println("-------------- MOYENNE ETUDIANT ---------------");
                        System.out.print("Entrez le numero d'etudiant : ");
                        key = sc.nextInt();
                        if(!hashMapEtudiants.containsKey(key)) System.out.println("Le numero est incorrect!");
                        else System.out.println("Moyenne d'etudiant : " + hashMapEtudiants.get(key).moyenneEtudiant(key));
                        System.out.println("-----------------------------------------------\n");
                        break;
                    case 6:
                        System.out.println("---------------- ADMISSION/NON ----------------");
                        System.out.print("Entrez le numero d'etudiant : ");
                        key = sc.nextInt();
                        double moyenne = hashMapEtudiants.get(key).moyenneEtudiant(key);
                        if (moyenne >= 10) System.out.println("Etudiant selectionne est admis.");
                        else System.out.println("Etudiant selectionne n'est pas admis.");
                        System.out.println("-----------------------------------------------\n");
                        break;
                    case 7:
                        System.out.println("--------------- LISTE ETUDIANTS ---------------");
                        Etudiant.listEtudiants();
                        System.out.println("-----------------------------------------------\n");
                        break;
                    case 8:
                        System.out.println("------------ STATISTIQUE GENERALE -------------");
                        Etudiant.listeValidationAnnee();
                        System.out.println("-----------------------------------------------\n");
                        break;
                    case 9:
                        System.out.println("Vous avez quitte le programme.");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Option incorrecte!");
                        break;
                }
            } catch ( NumberFormatException e) { System.out.print("Erreur! Veuillez reessayer.\n");
            } catch (ClassCastException e) { System.out.print("Impossible de modifier la note car l'etudiant \nn'est pas en derniere annee !\n");}
        }
    }

    public static void nouveauEtudiant() {
        keyInit ++;

        System.out.print("Entrez le nom : ");
        String nom = sc.next();
        System.out.print("Entrez le prenom : ");
        String prenom = sc.next();

        do {
            System.out.print("\nEntrez le niveau (INGE1, INGE2, INGE3) : ");
            String niveau = sc.next();
            if (niveau.equals("INGE1")) {
                ArrayList<Matiere> matieres = Matiere.remplirNotes(listeMatieresINGE1);
                Etudiant etudiant = new Etudiant(niveau ,nom, prenom, 0, matieres);
                hashMapEtudiants.put(keyInit, etudiant);
                hashMapEtudiants.get(keyInit).moyenneEtudiant(keyInit);
                break;
            }
            if (niveau.equals("INGE2")) {
                ArrayList<Matiere> matieres = Matiere.remplirNotes(listeMatieresINGE2);
                Etudiant etudiant = new Etudiant(niveau ,nom, prenom, 0, matieres);
                hashMapEtudiants.put(keyInit, etudiant);
                hashMapEtudiants.get(keyInit).moyenneEtudiant(keyInit);
                break;
            }
            if (niveau.equals("INGE3")) {
                double noteProjet;
                ArrayList<Matiere> matieres = Matiere.remplirNotes(listeMatieresINGE3); // recupere la liste des matieres
                System.out.print("Entrez la note projet obtenue : ");
                while ((noteProjet = sc.nextDouble()) < 0 || noteProjet > 20) {
                    System.out.print("Entrez la note projet obtenue : ");
                }
                EtudiantDerniere etudiant = new EtudiantDerniere(niveau, nom, prenom,0, matieres, noteProjet);
                hashMapEtudiants.put(keyInit, etudiant);
                hashMapEtudiants.get(keyInit).moyenneEtudiant(keyInit);
                break;
            }
        } while(true);
    }

    public static void modifierEtudiant(int key){
        System.out.print("------------- MODIFICATION ETUDIANT -----------");
        System.out.print("\n0 ⎢ QUITTER & ENRGST   ⎢⎥ MODIFIER NOTE: NO ⎥ 5" +
                         "\n1 ⎢ MODIFIER LE NOM    ⎢⎥ MODIFIER NOTE: NE ⎥ 4" +
                         "\n2 ⎢ MODIFIER LE PRENOM ⎢⎥ MODIFIER NOTE: NP ⎥ 3\n" );
        boolean option = true;
        int index = 0;

        while (option) {
            System.out.print("Entrez l'option souhaitee ➤ ");
            switch (Integer.parseInt(sc.next())) {
                case 1:
                    System.out.print("Entrez le nom : ");
                    hashMapEtudiants.get(key).setNom(sc.next());
                    break;
                case 2:
                    System.out.print("Entrez le prenom : ");
                    hashMapEtudiants.get(key).setPrenom(sc.next());
                    break;
                case 3:
                    System.out.print("Entrez la note projet : ");
                    double noteProjet  = sc.nextDouble(); // important pour pouvoir obtenir objet(etudiant) appartenant a EtudiantDerniere
                    ((EtudiantDerniere) hashMapEtudiants.get(key)).setNoteProjet(noteProjet); // sinon cercle infini avec probleme static-this.
                    break;
                case 4: // on verifie le niveau d'etudiant puis, en fonction de ce niveau on recupere la liste des matieres correspondantes
                    if(hashMapEtudiants.get(key).getNiveau().equals("INGE1")) index = choixMatiere(listeMatieresINGE1);
                    if(hashMapEtudiants.get(key).getNiveau().equals("INGE2")) index = choixMatiere(listeMatieresINGE2);
                    if(hashMapEtudiants.get(key).getNiveau().equals("INGE3")) index = choixMatiere(listeMatieresINGE3);
                    System.out.print("Entrez la note ecrite : "); // choixMatiere affiche indices et chaque elemetnt de la liste selectionnee
                    double notEcrite;
                    while ((notEcrite = sc.nextDouble()) < 0 || notEcrite > 20) {
                        System.out.print("Entrez une note entre 0 et 20 ! : ");
                    } // get(index) permet de choisir l'idex de matiere a modifier
                    hashMapEtudiants.get(key).getMatieres().get(index).setNotEcrite(notEcrite);
                    break;
                case 5:
                    if(hashMapEtudiants.get(key).getNiveau().equals("INGE1")) index = choixMatiere(listeMatieresINGE1);
                    if(hashMapEtudiants.get(key).getNiveau().equals("INGE2")) index = choixMatiere(listeMatieresINGE2);
                    if(hashMapEtudiants.get(key).getNiveau().equals("INGE3")) index = choixMatiere(listeMatieresINGE3);
                    System.out.print("Entrez la note orale : ");
                    double noteOrale;
                    while ((noteOrale = sc.nextDouble()) < 0 || noteOrale > 20) {
                        System.out.print("Entrez une note entre 0 et 20 ! : ");
                    }
                    hashMapEtudiants.get(key).getMatieres().get(index).setNoteOrale(noteOrale);
                    break;
                case 0 :
                    System.out.print("---------- MODIFICATIONS ENREGISTREES ---------\n");
                    option = false;
                    break;
                default : System.out.println("Option incorrecte!");
            }
        }
    }
    public static int choixMatiere(String[] liste){
        for (String nomMatiere : liste)
            System.out.println(nomMatiere + " - " + Arrays.asList(liste).indexOf(nomMatiere));
        System.out.println("Entrez le numero de matiere a modifier : ");
        return sc.nextInt();
    }
}
