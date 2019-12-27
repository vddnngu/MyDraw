package ServerClient;

enum Cods {
    ClientCode_GetSFD(1),
    ClientCode_GetCS(2),
    ClientCode_AddSFD(3),
    ClientCode_AddCS(4),
    ClientCode_DelSFD(5),
    ClientCode_DelCS(6),
    ServerCode_SetSFD(7),
    ServerCode_SetCS(8),
    ServerCode_DataNotActual(9),
    TestCode(10);

    private final int value;

    Cods(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Cods valueOf(int index) {
        switch (index) {
            case 1: return Cods.ClientCode_GetSFD;
            case 2: return Cods.ClientCode_GetCS;
            case 3: return Cods.ClientCode_AddSFD;
            case 4: return Cods.ClientCode_AddCS;
            case 5: return Cods.ClientCode_DelSFD;
            case 6: return Cods.ClientCode_DelCS;
            case 7: return Cods.ServerCode_SetSFD;
            case 8: return Cods.ServerCode_SetCS;
            case 9: return Cods.ServerCode_DataNotActual;
            default: return Cods.TestCode;
        }
    }

}
