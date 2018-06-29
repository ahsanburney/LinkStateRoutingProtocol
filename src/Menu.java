

/*
 * CS54204 2017 Link State Routing Simulator(Dijkstra�s algorithm)
 * Menu class is used to call different operations such as:
 * Create a Network Topology
 * Build a Forward Table
 * Shortest Path to Destination Router
 * Modify a Topology (Change the status of the Router)
 * Best Router for Broadcast
 *
 * @author Ahsan Burney
 */


import java.io.*;
        import java.util.ArrayList;
        import java.util.Scanner;

public class Menu {
    static int[][] topologyMatrix;
    static int broadcastNode;
    static int finalNodeMatrix[][];
    static int max;
    static int costBestNode;
    static ArrayList<Integer> totalNodes = new ArrayList<Integer>();
    static int[] shortestDistance = DijkstraAlgorithm.root;
    static int delNode;
    static int sNode;
    private static int[][] matrix;

    static {
        max = Integer.MAX_VALUE;
        sNode = max;
        delNode = max;

    }

    static int dNode;

    static {
        dNode = max;
    }

    static int d2Node;

    static {
        d2Node = max;
    }

    public static void main(String[] args) throws Exception {
        try{
            int check;
            Scanner scanner;
            int command;
            scanner = new Scanner(System.in);
            check = 0;
            command = 0;

            //Printing the command options to choose
            printdata("CS542 Link State Routing Simulator\n");

            do {
                printdata("(1)Create a Network Topology");
                printdata("(2)Build a Forward Table");
                printdata("(3)Shortest Path to Destination Router");
                printdata("(4)Modify a Topology (Change the status of the Router)");
                printdata("(5)Best Router for Broadcast");
                printdata("(6) Exit");
                printdata("Command:");
                command = input();

                //Checking if command is greater than Integer 6 or less then Integer 1
                if (command > 6 || command < 1) {
                    printdata("Please Enter a valid Command number between 1 and 6\n ");
                } else if (command != 1 && check == 0) {
                    printdata("Please First Enter Command 1 to give the Topology File\n ");
                    continue;
                }

                //Calling each Command Function
                if (command == 1) {
                    check = 1;
                    commandCall1();
                }

                else if (command == 2) {
                    commandCall2();
                }

                else if (command == 3) {
                    commandCall3(1);
                }

                else if (command == 4) {
                    commandCall4();
                    commandCall3(2);
                }

                else if (command == 5) {
                    commandCall5();
                }

                else if (command == 6) {
                    printdata("Exit CS542-04 2017 Fall project. Good Bye!");
                    printdata("");

                }
            } while (command != 6);
        } catch (Exception e) {
            printdata("Something is not right.\n Please Try again");
        }
    }

    //Ask the user to input Topology File and Matrix Implementation for Network topology
    public static void commandCall1() {
        try {
            Scanner scanner;
            int command;
            scanner = new Scanner(System.in);
            boolean checkFile; // Check if file is valid format
            String inputFile;
            printdata("Input original network topology matrix data file:");
            inputFile = scanner.next();

            if (new File(inputFile).isFile()) {
                checkFile = true;

            } else {
                checkFile = false;
                printdata("Input original network topology matrix data file:");
            }
            if (!checkFile) {
                printdata("Cannot generate the matrix!\\n Either file not present or some other Error.\\n");
            } else {
                totalNodes.clear(); //File is valid call the matrix implementation function to create network topologu
                topologyMatrix = matrixImplementation(inputFile);
                int matrixSize = 1;
                while (true) {
                    if (!(matrixSize <= topologyMatrix.length)) break;
                    totalNodes.add(matrixSize);
                    matrixSize++;
                }
                printdata("Review original topology matrix:");
                int l; //Iterating through the loop and to print the matrix
                for(l = 0; l<topologyMatrix.length; l++){
                    int k=0;
                    while (k<topologyMatrix.length) {
                        System.out.printf("%2d ", topologyMatrix[l][k]);
                        k++;
                    }
                    printdata("");
                }
            }
            System.out.println();
        } catch (Exception e) {
            printdata("Something is not right.\n Please Try again");
        }
    }
    //=============================================
    //If Command 2 is pressed the task is to build the forward table
    public static void commandCall2(){
        try {
            Scanner scanner;
            int command;
            int i;
            int j = 0;
            scanner = new Scanner(System.in);
            int[] root;
            //If matriz size is zero then ask for input topology file
            if (topologyMatrix.length == 0) {
                printdata("Please First Input Network Topology File ");
            } else {
                printdata("Select a source router:");
                sNode = input(); //Ask for source router number
                if (totalNodes.contains(sNode)) { //If source router number is valid
                    i = 0;
                    while (true) {
                        if (!(i < totalNodes.size())) break;
                        if (totalNodes.get(i) != sNode) {
                        } else {
                            sNode = i;
                        }
                        i++;
                    }
                    DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();// Call DijkstraAlgorithm class to pass the parameters
                    dijkstraAlgorithm.dijkstra(topologyMatrix, sNode);
                    root = DijkstraAlgorithm.root;
                    shortestDistance = DijkstraAlgorithm.sDistance;
                    //print the forward table
                    printdata("Router " + totalNodes.get(sNode) + " Connection Table");
                    printdata("Destination \t Interface");
                    printdata("=============================");
                    while (j < topologyMatrix.length) {
                        if (j != sNode) {
                            if (shortestDistance[j] != 0 && shortestDistance[j] != max) {
                                System.out.print("     " + totalNodes.get(j) + "\t\t");
                                nextNode(root, j, sNode);
                                System.out.print("\n");
                            } else {
                                System.out.printf("     " + totalNodes.get(j) + "\t\t  -\n");
                            }
                        } else {
                            System.out.printf("     " + totalNodes.get(j) + "\t\t  -\n");
                        }
                        j++;
                    }
                } else {
                    printdata("Try different source router\n");
                }
            }
            System.out.println();
        } catch (Exception e) {
            printdata("Something is not right.\n Please Try again");
        }
    }
    //=========================================
    // To Find the Shortest Path to Destination Router
    public static void commandCall3(int check){
        try {
            Scanner scanner;
            int command;
            int[] ints;
            scanner = new Scanner(System.in);// if topology not present ask for file
            if (topologyMatrix.length == 0 && sNode == max) {
                printdata("Please First Input Network Topology File ");
            } else {
                if (sNode != max) {
                } else { //ask for source router and pass to algorithm to evaluate connections
                    printdata("Select the source router:");
                    sNode = input();
                    DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
                    int i = 0;
                    while (i < totalNodes.size()) {
                        if (totalNodes.get(i) == sNode)
                            sNode = i;
                        i++;
                    }
                    dijkstraAlgorithm.dijkstra(topologyMatrix, sNode);
                }
                if(check==1) {//ask for destination router
                printdata("Select the destination router:");
                dNode = scanner.nextInt();
                d2Node=dNode;}
                else if(d2Node==max)
                	return;
                else
                	dNode=d2Node;
                if (!totalNodes.contains(dNode)) {
                    printdata("Select another router for destination");
                    printdata("");
                }
                int i = 0;
                while (i < totalNodes.size()) {
                    if (totalNodes.get(i) == dNode)
                        dNode = i;
                    i++;
                }
                int ints1[] = DijkstraAlgorithm.root;
                shortestDistance = DijkstraAlgorithm.sDistance;
                boolean flag = true;
                //if no path present
                if (shortestDistance[dNode] <= 0
                        || shortestDistance[dNode] == max) {
                    System.err.println("There is no path to this destination!");
                } else {//print the shortest path and the cost
            System.out.print("The shortest path from " + totalNodes.get(sNode) + " to "+ totalNodes.get(dNode) + " is ");
            System.out.print(totalNodes.get(sNode));
            getPath(ints1, dNode);
            printdata("\nThe total cost is " + shortestDistance[dNode]);
        }
            }
            System.out.println();
        } catch (Exception e) {
            printdata("Something is not right.\n Please Try again");
        }
    }
    //=========================================
    // this function is called if modify the topology option is called
    public static void commandCall4(){
        try {// check if sourse node is choosen
            int i = 0;//if sourse node is not equal to maximum integer
            if (sNode != max) {
            } else {
                System.out.println("Select the source router:");
                sNode = input();
                DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
                int p = 0;
                while (true) {
                    if (!(p < totalNodes.size())) break;
                    if (totalNodes.get(p) != sNode) {
                    } else {
                        sNode = p;
                    }
                    p++;
                }
                dijkstraAlgorithm.dijkstra(topologyMatrix, sNode);
            }//ask for router number to be deleted
            printdata("Delete a router from this network topology");
            delNode = input();
            while (true) {
                if (!(i < totalNodes.size())) break;
                if (totalNodes.get(i) != delNode) {
                } else {
                    delNode = i;
                }
                i++;
            }
            while (true) {
                if ((Menu.totalNodes.contains(delNode))) {
                    break;
                } else {//if entered router numbr is not valid
                    printdata("Router number not valid. Please enter valid Router number");
                    delNode = input();
                }
            }
            Integer remove = totalNodes.remove(delNode);
            topologyMatrix = nodeRemovalFromMatrix(topologyMatrix, delNode);
            if (delNode == sNode || sNode == max) {
            } else {
                getTable();
            }
            printdata("");


            System.out.println();
        } catch (Exception e) {
            printdata("Something is not right.\n Please Try again");
        }
    }
    //=========================================
   
//=========================================
     //To check the best router for broadcast this function is called
    public static void commandCall5(){
        try {//passing the values to the topology matrix
            switch (broadcastNode = broadcast(topologyMatrix)) {
            }
            if (costBestNode <= 0 || costBestNode == max) {
                printdata("There isn't any best router available");
            } else {
                printdata("The Best Router to Broadcast is " + broadcastNode);
                printdata("Cost is " + costBestNode);
            }
            printdata("");

        } catch (Exception e) {
            printdata("Something is not right.\n Please Try again");
        }
    }
    //=========================================
    //to check if entered values by user is integer or not
    public static int input(){
        Scanner scan = new Scanner(System.in);
        while(true){
            String str = scan.next();
            int flag=1;
            for(int i =0;i<str.length();i++)
            {
                int a = str.charAt(i)-'0';
                if(!(a>=0&&a<=9))
                {
                    flag=0;
                    break;
                }

            }
            if(flag==0)
            {
                System.out.println("PLEASE ENTER INTEGER VALUE");

            }
            else
                return Integer.parseInt(str);

        }
    }
    //============================
    //To evaluate the path this function is called
    static void getPath(int store[], int nodeData) {
        switch (store[nodeData]) {
            case -1:
                return;
        }
        getPath(store, store[nodeData]);
        System.out.print("-->"+Menu.totalNodes.get(nodeData));
    }
    //Print String function
    public static void printdata(String str) {
        System.out.println(str);

    }
    //=================================
// to modify the matrix this function is called it takes the rows and colums of the topology and delete the
    //node that is been asked by the user and gives back the result
    static int[][] nodeRemovalFromMatrix(int graphData[][], int nodeNumber) {
        finalNodeMatrix = new int[graphData.length - 1][graphData.length - 1];
        int first = 0;
        int i = 0;
        while (i < graphData.length) {
            if (i != nodeNumber) {
                int second = 0;
                int j = 0;
                while (j < graphData.length) {
                    if (j != nodeNumber) {
                        finalNodeMatrix[first][second] = graphData[i][j];
                        second++;
                    }
                    j++;
                }

                first++;
            }
            i++;
        }
        return finalNodeMatrix;
    }
    //==========================
    static void nextNode(int storeValue[], int destinationRoute, int sourceRoute) {
        switch (storeValue[destinationRoute]) {
            case -1:
                return;
        }
        nextNode(storeValue, storeValue[destinationRoute], sourceRoute);
        if (storeValue[destinationRoute] != sourceRoute) {
            return;
        }
        System.out.printf("%5d", Menu.totalNodes.get(destinationRoute));
    }

    //This function creates the topology after the file is been given by the user
    public static int[][] matrixImplementation(String matrixFile)
            throws IOException {
        try {
            String line;
            int count = 0;
            FileInputStream fileInputStream;
            DataInputStream dataInputStream;
            BufferedReader bufferedReader;
            fileInputStream = new FileInputStream(matrixFile);
            dataInputStream = new DataInputStream(fileInputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String line1;
            while (true)
            {
                if (!((line1 = bufferedReader.readLine()) != null)) break;
                count++;
            }
            bufferedReader.close();
            int N=count;
            int nodes=N;
            int[][] fileData = new int[N][N];
            FileInputStream fileInputStream1 = new FileInputStream(matrixFile);
            DataInputStream dataInputStream1 = new DataInputStream(fileInputStream1);
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(dataInputStream1));
            String[] values;
            count = 0;
            while (true)
            {
                if (!((line = bufferedReader1.readLine()) != null)) {
                    break;
                }
                values = line.split(" ");
                int i = 0;
                while (i < nodes) {
                    fileData[count][i] = (int) Double.parseDouble(values[i]);
                    i++;
                }

                count++;
            }
            bufferedReader1.close();
            return fileData;
        } catch (FileNotFoundException error) {
            throw new FileNotFoundException();
        }
    }

    //========================
    // this function shows the connection table after router is removed from the topology
    static void getTable() {
        if (sNode != max) {
            int[] root;
            int[] cost;
            int i;
            printdata("Review original topology matrix:");
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
            dijkstraAlgorithm.dijkstra(finalNodeMatrix, sNode);
            root = DijkstraAlgorithm.root;
            cost = DijkstraAlgorithm.sDistance;
            printdata("Router " + Menu.totalNodes.get(sNode) + " Connection Table");
            printdata("Destination \t Interface");
            printdata("==============================");
            i = 0;
            while (i < finalNodeMatrix.length) {
                switch (cost[i]) {
                    case 0:
                    case Integer.MAX_VALUE:
                        System.out.printf("     " + Menu.totalNodes.get(i) + "\t\t  -");
                        printdata("");
                        break;
                    default:
                        PrintStream printf = System.out.printf("     " + Menu.totalNodes.get(i) + "\t\t");
                        nextNode(root, i, sNode);
                        printdata("");
                        break;
                }
                i++;
            }
        }
    }

    //=====================================
    //this function is used by the broacast feature to calculate the best router in the topology
    public static int broadcast(int[][] matrix) {
        Menu.matrix = matrix;
        costBestNode = max;
        int i1;
        int node;
        node = 0;
        int[] minDistanceValue;
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        int i = 0;
        while (true) {
            if (!(i < matrix.length)) break;
            i1 = 0;
            dijkstraAlgorithm.dijkstra(matrix, i);
            minDistanceValue = DijkstraAlgorithm.sDistance;
            int i2 = 0;
            while (true) {
                if (!(i2 < minDistanceValue.length)) break;
                int j = minDistanceValue[i2];
                i1 += j;
                i2++;
            }
            if (i1 >= costBestNode) {
            } else {
                costBestNode = i1;
                node = i;
            }
            i++;
        }
        return Menu.totalNodes.get(node);
    }

}


