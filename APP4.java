import java.util.*;
//----------------------------------------------------------------------------------------------------------------------
class Etudiant{
    private String nom, prenom;
    private double noteProjet, moyenne;

    public Etudiant(String nom, String prenom, double noteProjet, double moyenne){
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.noteProjet = noteProjet;
    }

    public void afficherEtudiant(int key) {
        System.out.println("Nom : " + nom + "\nPrenom : " + prenom +  "\nMoyenne : " + moyenne);
    }
}
//----------------------------------------------------------------------------------------------------------------------
class Matiere {
    private double noteOrale, notEcrite;
    private String nomMatiere;

    public Matiere(double notEcrite, double noteOrale,String nomMatiere){
        this.nomMatiere = nomMatiere;
        this.notEcrite = notEcrite;
        this.noteOrale = noteOrale;
    }
}

class EtudiantDerniere extends Etudiant{
    public EtudiantDerniere(String nom, String prenom, double noteProjet, double moyenne) {
        super(nom, prenom, noteProjet, moyenne);
    }
}


class EtudiantAutre extends Etudiant{
    public EtudiantAutre(String nom, String prenom, Double noteProjet, double moyenne) {
        super(nom, prenom, noteProjet, moyenne);
    }
}


public class APP4 {
    public static HashMap<Integer, Etudiant> Etudiants = new HashMap<>();
    public static HashMap<Integer, Matiere> Matieres = new HashMap<>();

    public static Scanner sc = new Scanner(System.in);
    private static int key = 0;

    public static void main(String[] args) {
        String menu = "------------------------------------------------\n" +
                "Afficher le menu          ]------------------[ 1\n" +
                "Ajouter un etudiant       ]------------------[ 2\n" +
                "Afficher un etudiant      ]------------------[ 3\n" +
                "Calculer la moyenne       ]------------------[ 4\n" +
                "Verifier l'admission      ]------------------[ 5\n" +
                "Supprimer un etudiant     ]------------------[ 6\n" +
                "Statistique d'admission   ]------------------[ 7\n" +
                "Quitter le programme      ]------------------[ 8\n" +
                "------------------------------------------------\n";
        System.out.print(menu);
        System.out.print("\nEntrez l'option souhaitee apres ce symbole ");
        while (true) {
            try {
                System.out.print("> : ");
                switch (Integer.parseInt(sc.next())) {
                    case 1 :
                        nouveauEtudiant();
                        System.out.print(menu);
                        break;
                    case 7 :
                        System.out.println("Vous avez quitte le programme.");
                        sc.close();
                        System.exit(0);
                        break;
                    default : System.out.println("Option incorrecte!");
                }
            } catch (IllegalStateException | NumberFormatException e) {
                System.out.println("Erreur. Veuillez recommencer!\n");
            } catch (InputMismatchException | NullPointerException e) {
                System.out.print("Aucun employe ne correspond a ce numero.\n");
            }
        }
    }

    public static void nouveauEtudiant() {
        key++;
        System.out.print("\n--------------- NOUVEAU ETUDIANT --------------\n");
        System.out.print("Entrez le nom : ");
        String nom = sc.next();
        System.out.print("Entrez le prenom : ");
        String prenom = sc.next();

        ArrayList <String> listeMatieres = new ArrayList<>();
        Collections.addAll(listeMatieres, "Histoire-Geo", "Mathematiques", "Langues");

        for (String nomMatiere: listeMatieres){
            System.out.print("La note ecrite obtenue en " + nomMatiere + " : ");
            double notEcrite = Double.parseDouble(sc.next());
            System.out.print("La note orale obtenue en " + nomMatiere + " : ");
            double noteOrale = Double.parseDouble(sc.next());
            Matiere matiere = new Matiere(notEcrite, noteOrale, nomMatiere );
            Matieres.put(key, matiere);
        }

        System.out.print("\nEntrez la note du Projet si l'etudiant est en derniere annee.\n sinon entrez 0 : ");
        double noteProjet = Double.parseDouble(sc.next());
        if (noteProjet == 0){
            EtudiantAutre etudiant = new EtudiantAutre(nom, prenom, null, 0);
            Etudiants.put(key, etudiant);
        }
        else {
            EtudiantDerniere etudiant = new EtudiantDerniere(nom, prenom, noteProjet, 0);
            Etudiants.put(key, etudiant);
        }

        System.out.println("\nEtudient est bien ajoute dans la liste.\nLe numero d'etudiant : " + key);
        System.out.print("------------------------------------------------\n");
    }
}