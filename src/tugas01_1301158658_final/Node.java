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
public class Node {
    
    private String nama = "";
    private double costHeuristic;
    private double costF;
    private ArrayList<Child> child = new ArrayList<>();
    private Node parent = null;
    
    public Node (String nama, double costHeuristic){
        this.nama = nama;
        this.costHeuristic = costHeuristic;
    }
    
    public Node(){
        
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getCostHeuristic() {
        return costHeuristic;
    }

    public void setCostHeuristic(double costHeuristic) {
        this.costHeuristic = costHeuristic;
    }

    public Child getChild(int index) {
        return child.get(index);
    }
    
    public void addChild(Child c){
        child.add(c);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public int getSizeChild(){
        return child.size();
    }

    public double getCostF() {
        return costF;
    }

    public void setCostF(double costF) {
        this.costF = costF;
    }
    
    
}
