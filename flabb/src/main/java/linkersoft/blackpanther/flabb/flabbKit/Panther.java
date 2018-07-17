package linkersoft.blackpanther.flabb.flabbKit;

import android.graphics.Path;

/**
 * Created by LiNKeR on 7/17/2018.
 */
public class Panther{
    Path path;
    int[][] Nodes,Controls;
    private static final int X=0,Y=1;
    int noOfNodes,noOfControls;

    protected Panther(int noOfNodes,int noOfControls,int[][]Nodes,int[][]Controls){
        path();
        this.noOfNodes=noOfNodes;
        this.noOfControls=noOfControls;
        this.Nodes=Nodes;
        this.Controls=Controls;
        Freese();
    }
    private void Freese(){
        int cntrl0Indx=0,cntr1Indx=1;
        path.moveTo(Nodes[0][X], Nodes[0][Y]);
        for (int nodeIndx = 0; nodeIndx < Nodes.length; nodeIndx++) {
            if (nodeIndx != Nodes.length - 1) {
                BeZierThrough(
                        Controls[cntrl0Indx][X], Controls[cntrl0Indx][Y],
                        Controls[cntr1Indx][X], Controls[cntr1Indx][Y],
                        Nodes[nodeIndx + 1][X], Nodes[nodeIndx + 1][Y]);
                cntr1Indx+=2;
                cntrl0Indx+=2;
            }else{
                BeZierThrough(
                        Controls[cntrl0Indx][X], Controls[cntrl0Indx][Y],
                        Controls[cntr1Indx][X], Controls[cntr1Indx][Y],
                        Nodes[0][X], Nodes[0][Y]);
            }
        }
        path.close();
    }
    private void BeZierThrough(int cntr0X,int cntr0Y,int cntr1X,int cntr1Y,int endX,int endY){
        path.cubicTo( cntr0X,cntr0Y,  cntr1X,cntr1Y,  endX,endY);
    }
    private void path(){
        path=new Path();
    }
}
