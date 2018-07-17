package linkersoft.blackpanther.flabb.flabbKit;

import android.animation.TypeEvaluator;

/**
 * Created by LiNKeR on 7/17/2018.
 */
public class PantherEvaluator  implements TypeEvaluator<Panther> {
    private int noOfNodes,noOfControls,X=0,Y=1,Nodes[][],Controls[][];

    protected PantherEvaluator(int noOfNodes,int noOfControls){
        this.noOfNodes=noOfNodes;this.noOfControls=noOfControls;
        Nodes =new int[noOfNodes][2];
        Controls =new int[noOfControls][2];
    }
    public Panther evaluate(float fraction, Panther In, Panther Fin){
        for (int i = 0; i <noOfNodes ; i++) {
            Nodes[i][X]=Math.round((1-fraction)*In.Nodes[i][X]+ fraction*Fin.Nodes[i][X]);
            Nodes[i][Y]=Math.round((1-fraction)*In.Nodes[i][Y]+ fraction*Fin.Nodes[i][Y]);
        }
        for (int i = 0; i <noOfControls ; i++) {
            Controls[i][X]=Math.round((1-fraction)*In.Controls[i][X]+ fraction*Fin.Controls[i][X]);
            Controls[i][Y]=Math.round((1-fraction)*In.Controls[i][Y]+ fraction*Fin.Controls[i][Y]);
        }
        Panther currPanther=new Panther(noOfNodes,noOfControls,Nodes,Controls);
        return currPanther;
    }


}
