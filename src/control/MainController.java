package control;

import model.File;
import model.List;


/**
 * Created by Jean-Pierre on 05.11.2016.
 */
public class MainController {

    private List<File>[] allShelves;    //Ein Array, das Objekte der Klasse Liste verwaltet, die wiederum Objekte der Klasse File verwalten.

    public MainController(){
        allShelves = new List[2];
        allShelves[0] = new List<File>(); //Beachtet die unterschiedliche Instanziierung! Was bedeutet das?
        allShelves[1] = new List<File>();
        createFiles();
    }

    /**
     * Die Akten eines Regals werden vollständig ausgelesen.
     * @param index Regalnummer
     * @return String-Array mit den Familiennamen
     */
    public String[] showShelfContent(int index){
        int elementcount = 0;
        List<File> list = allShelves[index];
        list.toFirst();
        while (list.hasAccess()) {
            elementcount++;
            list.next();
        }
        String[] shelfCont = new String[elementcount];
        list.toFirst();
        for (int i = 0; i < shelfCont.length; i++) {
            shelfCont[i] = list.getContent().getName();
            list.next();
        }
        return  shelfCont;
        //TODO 03: Ausgabe der Inhalte

    }

    /**
     * Ein Regal wird nach Familiennamen aufsteigend sortiert.
     * @param index Regalnummer des Regals, das sortiert werden soll.
     * @return true, falls die Sortierung geklappt hat, sonst false.
     */
    public boolean sort(int index){
        //TODO 07: Sortieren einer Liste.
        return false;
    }

    /**
     * Die gesammte Aktensammlung eines Regals wird zur Aktensammlung eines anderen Regals gestellt.
     * @param from Regalnummer, aus dem die Akten genommen werden. Danach sind in diesem Regal keine Akten mehr.
     * @param to Regalnummer, in das die Akten gestellt werden.
     * @return true, falls alles funktionierte, sonst false.
     */
    public boolean appendFromTo(int from, int to){
        //TODO 04: Die Objekte einer Liste an eine andere anhängen und dabei die erste Liste leeren.
        if((from==0 || from == 1) && (to == 0 || to == 1)){
            allShelves[to].concat(allShelves[from]);
            return true;
        }
        return false;
    }

    /**
     * Es wird eine neue Akte erstellt und einem bestimmten Regal hinzugefügt.
     * @param index Regalnummer
     * @param name Name der Familie
     * @param phoneNumber Telefonnummer der Familie
     * @return true, falls das Hinzufügen geklappt hat, sonst false.
     */
    public boolean appendANewFile(int index, String name, String phoneNumber){
        File file = new File(name,phoneNumber);
        allShelves[index].append(file);
        allShelves[index].toLast();
        if(allShelves[index].getContent() == file){
            return true;
        }
        return false;
    }

    /**
     * Es wird eine neue Akte in ein Regal eingefügt. Funktioniert nur dann sinnvoll, wenn das Regal vorher bereits nach Namen sortiert wurde.
     * @param index Regalnummer, in das die neue Akte einsortiert werden soll.
     * @param name Name der Familie
     * @param phoneNumber Telefonnummer der Familie
     * @return true, falls das Einfügen geklappt hat, sonst false.
     */
    public boolean insertANewFile(int index, String name, String phoneNumber){
        //TODO 08: Einfügen einer neuen Akte an die richtige Stelle innerhalb der Liste.
        return false;
    }

    /**
     * Es wird nach einer Akte gesucht.
     * @param name Familienname, nach dem gesucht werden soll.
     * @return Zahlen-Array der Länge 2. Bei Index 0 wird das Regal, bei Index 1 die Position der Akte angegeben. Sollte das Element - also die Akte zum Namen - nicht gefunden werden, wird {-1,-1} zurückgegeben.
     */
    public int[] search(String name){
        for (int i = 0; i < allShelves.length; i++) {
            int elementcount = 0;
            List<File> list = allShelves[i];
            list.toFirst();
            while (list.hasAccess()) {
                elementcount++;
                list.next();
            }
            list.toFirst();
            for (int j = 0; j < elementcount; j++) {
                if(allShelves[i].getContent().getName().equals(name)){
                    return new int[]{i,j};
                }
                allShelves[i].next();
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * Eine Akte wird entfernt. Dabei werden die enthaltenen Informationen ausgelesen und zurückgegeben.
     * @param shelfIndex Regalnummer, aus dem die Akte entfernt wird.
     * @param fileIndex Aktennummer, die entfernt werden soll.
     * @return String-Array der Länge 2. Index 0 = Name, Indedx 1 = Telefonnummer.
     */
    public String[] remove(int shelfIndex, int fileIndex){
        //TODO 06: Entfernen aus einer Liste.

        List<File> list = allShelves[shelfIndex];
        String[] stringToReturn;
        list.toFirst();
        for (int i = 0; i < fileIndex; i++) {
            list.next();
        }
        stringToReturn = new String[]{allShelves[shelfIndex].getContent().getName(),allShelves[shelfIndex].getContent().getPhoneNumber()};

        return stringToReturn;
    }

    /**
     * Es werden 14 zufällige Akten angelegt und zufällig den Regalen hinzugefügt.
     */
    private void createFiles(){
        for(int i = 0; i < 14; i++){
            int shelfIndex = (int)(Math.random()*allShelves.length);

            int nameLength = (int)(Math.random()*5)+3;
            String name = "";
            for(int j = 0; j < nameLength; j++){
                name = name + (char) ('A' + (int)(Math.random()*26));
            }

            int phoneLength = (int)(Math.random()*2)+8;
            String phone = "0";
            for (int k = 1; k < phoneLength; k++){
                phone = phone + (int)(Math.random()*10);
            }

            appendANewFile(shelfIndex,name,phone);
        }
    }
}
