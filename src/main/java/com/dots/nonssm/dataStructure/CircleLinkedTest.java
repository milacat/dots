package com.dots.nonssm.dataStructure;

import java.util.ArrayList;
import java.util.List;

public class CircleLinkedTest {

    private static final int len = 10;
    private static Node firstNode;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        creatLink();
        checkCircle();
        Node entry = findCircleEntry2();
        if(entry!=null){
            System.out.println("环的入口节点node.object="+entry.data);
        }
    }

    /**
     * 查找到环的入口
     */
    private static Node findCircleEntry() {
        Node p = firstNode;
        Node q = firstNode;
        int pSteps = 0, qSteps = 0;
        while (null != q.next) {
            q = q.next;
            ++qSteps;
            while(p.next != null){
                p = p.next;
                ++pSteps;
                if(p ==q && pSteps == qSteps){
                    break;
                }
                else if(p ==q && pSteps != qSteps){
                    return p;
                }

            }
            p = firstNode;
            pSteps = 0;
        }
        return  null;
    }
    private static Node findCircleEntry2() {
       List<Node> nodes = new ArrayList<>();
       Node p = firstNode;
       nodes.add(p);
       while(p.next != null){
           p = p.next;
           if(nodes.contains(p)){
               return p;
           }
           nodes.add(p);
       }
       return null;
    }

    /**
     * 检查链表中是否有环
     */
    private static boolean checkCircle() {
        Node fastCursor = firstNode;
        Node slowCursor = firstNode;
        while (fastCursor != null){
            if(fastCursor.next != null && fastCursor.next.next!=null){
                fastCursor = fastCursor.next.next;
            }
            else{
                return false;
            }
            slowCursor = slowCursor.next;
            if(fastCursor == slowCursor){
                System.out.println("链表中有环");
                return true;
            }
        }
        return false;

    }

    public static void creatLink(){
        Node temp = null;
        Node entry = null;
        for(int i = 1; i <= len ; i++){
            // 处理首节点
            if(i==1){
                Node  nd= new Node(i);
                firstNode = nd;
                temp = nd;
            }else if(i == 5){
                Node nd = new Node(i);
                temp.next = nd;
                temp = nd;
                entry = nd;
            }else if(i == len){    //处理末节点
                Node nd = new Node(i);
                temp.next = nd;
                temp = nd;
                temp.next = entry;
            }else {
                Node nd = new Node(i);
                temp.next = nd;
                temp = nd;
            }
        }
        System.out.println("环形链表初始化完成");
    }
}
