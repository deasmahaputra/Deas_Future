/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas01_1301158658_final;

/**
 *
 * @author DEAS
 */
public class Child {
    
    private double cost;
    private Node target;
    
    public Child(Node target, double cost){
        this.target = target;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }
    
}
