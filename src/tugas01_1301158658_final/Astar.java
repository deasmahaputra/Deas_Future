/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas01_1301158658_final;

import java.util.ArrayList;

/**
 *
 * @author DEAS
 */
public class Astar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Node bobbia = new Node("BOBBIA", 10.5);
        Node piacenza = new Node("PIACENZA", 10.5);
        Node terme = new Node("TERME", 7);
        Node emilia = new Node("EMILIA", 6);
        Node carpi = new Node("CARPI", 8);
        Node imola = new Node("IMOLA", 5);
        Node faenza = new Node("FAENZA", 4);
        Node ferrara = new Node("FERRARA", 5);
        Node forli = new Node("FORLI", 2);
        Node cesena = new Node("CASENA", 4.5);
        Node rimini = new Node("RIMINI", 0.5);
        Node ravenna = new Node("RAVENNA", 0);
        Node baru = new Node("Baru", 6);

        baru.addChild(new  Child(bobbia, 3));
        baru.addChild(new Child(piacenza, 5));
        
        bobbia.addChild(new Child(piacenza, 5));
        bobbia.addChild(new Child(terme, 3));
        bobbia.addChild(new Child(cesena, 15));

        piacenza.addChild(new Child(carpi, 3));
        piacenza.addChild(new Child(terme, 3));

        terme.addChild(new Child(emilia, 2));
        terme.addChild(new Child(faenza, 3));

        carpi.addChild(new Child(emilia, 2));
        carpi.addChild(new Child(ferrara, 8));

        emilia.addChild(new Child(imola, 2));

        imola.addChild(new Child(faenza, 1));
        imola.addChild(new Child(forli, 3));

        faenza.addChild(new Child(forli, 2));
        faenza.addChild(new Child(cesena, 6));

        ferrara.addChild(new Child(ravenna, 6));

        forli.addChild(new Child(cesena, 2));
        forli.addChild(new Child(ravenna, 3));

        cesena.addChild(new Child(rimini, 5));

        rimini.addChild(new Child(ravenna, 1));

        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();
        
        Astar al = new Astar();
        
        boolean goal = false;

        open.add(baru);

        while (goal == false) {
            Node node;
             node = al.bestNode(open);
            System.out.println("===================================================");
            System.out.println("\t \t PERHITUNGAN PADA " + node.getNama());
            System.out.println("===================================================");
            System.out.print("OPEN: " + "\n");
            al.display(open);
            System.out.print("CLOSED: " + "\n");
            al.display(closed);

            node = al.bestNode(open);
            System.out.println("BEST NODE: " +"\n"+ node.getNama()+"\n");
            if (node.getCostHeuristic() == 0.0) {
                goal = true;
                open.remove(al.searchNodeOpen(open, node));
                closed.add(node);
            } else {
                open.remove(al.searchNodeOpen(open, node));
                closed.add(node);

                for (int i = 0; i < node.getSizeChild(); i++) {
                    if (al.searchNodeOpen(open, node.getChild(i).getTarget()) == -1) {
                        node.getChild(i).getTarget().setParent(node);
                        open.add(node.getChild(i).getTarget());
                    } else {
                        Node tempParent = node.getChild(i).getTarget().getParent();
                        double total = al.costF(node.getChild(i).getTarget());

                        node.getChild(i).getTarget().setParent(node);
                        double total2 = al.costF(node.getChild(i).getTarget());

                        if (total < total2) {
                            node.getChild(i).getTarget().setParent(tempParent);
                        }
                    }

                }
            }
                System.out.println("Hasil penghitungan f(n) : ");
            for (int i = 0; i < open.size(); i++) {
                System.out.print(open.get(i).getNama() + " "+" f(n): " + al.costF(open.get(i)));
                System.out.println("");
            }
            System.out.println("");

        }

        System.out.println("===================================================");
        System.out.println("KESIMPULAN");
        System.out.print("Rute Solusi: ");
        al.getRute(ravenna);
        System.out.println("Jarak Yang Harus Ditempuh: " + al.distance(ravenna));
    }
    
     public int searchNodeOpen(ArrayList<Node> list, Node node) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNama().equals(node.getNama())) {
                index = i;
            }
        }
        return index;
    }
     
      public void getRute(Node node) {
        ArrayList<Node> list = new ArrayList<>();

        Node goal = node;
        list.add(node);
        Node parent = goal.getParent();
        while (parent != null) {
            list.add(parent);
            parent = parent.getParent();
        }
        for (int i = list.size() - 1; i > 0; i--) {
            System.out.print(list.get(i).getNama() + " -> ");
        }
        System.out.println(list.get(0).getNama());
    }


    public Node bestNode(ArrayList<Node> list) {

        double total;
        double costF = 9999;
        Node tempBest = null;

        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            total = costF(node);
            if (total < costF) {
                costF = total;
                tempBest = list.get(i);
            }
        }

        tempBest.setCostF(costF);

        return tempBest;
    }

    public double costF(Node node) {
        double total = node.getCostHeuristic();
        Node parent = node.getParent();
        while (parent != null) {
            for (int j = 0; j < parent.getSizeChild(); j++) {
                if (parent.getChild(j).getTarget().getNama().equals(node.getNama())) {
                    total += parent.getChild(j).getCost();
                }
            }
            node = parent;
            parent = parent.getParent();
        }
        return total;
    }

    public double distance(Node node) {
        double total = 0;
        Node parent = node.getParent();
        while (parent != null) {
            for (int j = 0; j < parent.getSizeChild(); j++) {
                if (parent.getChild(j).getTarget().getNama().equals(node.getNama())) {
                    total += parent.getChild(j).getCost();
                }
            }
            node = parent;
            parent = parent.getParent();
        }
        return total;
    }

    public void display(ArrayList<Node> list) {
        System.out.print("[");
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i).getNama() + ", ");
        }
        if (!list.isEmpty()) {
            System.out.print(list.get(list.size() - 1).getNama());
        }
        System.out.println("]");
    }

   
    
}
