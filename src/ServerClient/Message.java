package ServerClient;

import java.util.ArrayList;

public class Message {
    public Cods code;
    public ArrayList<String> data = new ArrayList<String>();

    @Override
    public String toString() {
        String res =  "\nCode: "+code.toString();
        switch (code)
        {
            case ClientCode_AddCS:
            case ClientCode_AddSFD:
            case ServerCode_SetCS:
            case ServerCode_SetSFD:
            case ClientCode_DelSFD:
                res+="\nSize of data: "+data.size();
                res+="\nData: ";
                for (String it : data){
                    res+="\n\t"+it;
                }
                ;break;
            case ClientCode_DelCS:
                res+="\nData: "+ data.get(0);
                break;
            case ClientCode_GetCS:
            case ClientCode_GetSFD:
            case ServerCode_DataNotActual:break;
        }

        return res;
    }
}
