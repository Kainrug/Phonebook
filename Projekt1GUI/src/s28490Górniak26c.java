import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class s28490Górniak26c {

    private static void mostTimeCalling(List<Call> listOfCalls , int N) {
        HashMap<Integer , Integer> map = new HashMap<>();

        for(Call call : listOfCalls) {
            if (map.containsKey(call.getCallerID())) {
                map.put(call.getCallerID(), map.get(call.getCallerID())+ call.getCallDuration());
            } else  {
                map.put(call.getCallerID(),call.getCallDuration());
            }
        }
        List<Client> clientList = new ArrayList<>(
        map.keySet().stream()
                .map(e -> {
            Client client = new Client(e);
            client.setCallDuration(map.get(e));
            return client;
        }).toList());

        clientList.sort((e1, e2) -> e2.getCallDuration() - e1.getCallDuration());
        clientList.stream().limit(N).forEach(client -> System.out.println(client.getCallerID() + " " + client.getCallDuration()));

    }

    private static void mostTimeGettingCalls(List<Call> callList, int N) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(Call call : callList) {
            if (map.containsKey(call.getReceiverID())) {
                map.put(call.getReceiverID(), map.get(call.getReceiverID()) + call.getCallDuration());
            } else  {
                map.put(call.getReceiverID(), call.getCallDuration());
            }
        }
        List<SecondClient> clientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e -> {
                    SecondClient secondClient = new SecondClient(e);
                            secondClient.setCallDuration(map.get(e));
                    return secondClient;
                        })
                        .toList());

        clientList.sort((e1, e2) -> e2.getCallDuration() - e1.getCallDuration());
        clientList.stream().limit(N).forEach(client -> System.out.println(client.getReceiverID() + " " + client.getCallDuration()));

    }

    private static void clientsWhoRangTheMostToOtherClients(List<Call> callList, int N) {
            HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();

        for (Call call : callList) {
            if (map.containsKey(call.getCallerID())) {
                 map.get(call.getCallerID()).add(call.getReceiverID());
            } else {
                map.put(call.getCallerID(), new TreeSet<>());
            }
        }

        List<Client> clientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e-> {
                           Client client = new Client(e);
                           client.setCallCounter(map.get(e).size());
                                    return client;
                        })
                        .toList()
        );

        clientList.sort((e1, e2) -> e2.getCallCounter() - e1.getCallCounter());
        clientList.stream().limit(N).forEach(client -> System.out.println(client.getCallerID() + " was ringing " + client.getCallCounter() + " times"));


    }

    private static void clientsWhoWereGettingCallsTheMostFromOtherClients(List<Call> callList, int N) {
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

            for (Call call : callList) {
                if (map.containsKey(call.getReceiverID())) {
                    map.get(call.getReceiverID()).add(call.getCallerID());
                } else {
                    map.put(call.getReceiverID(), new HashSet<>());
                }
            }

        List<SecondClient> clientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e-> {
                            SecondClient client = new SecondClient(e);
                            client.setCallCounter(map.get(e).size());
                            return client;
                        })
                        .toList()
        );

            clientList.sort((e1,e2) -> e2.getCallCounter() - e1.getCallCounter());
            clientList.stream().limit(N).forEach(client -> System.out.println(client.getReceiverID() + " was getting calls " + client.getCallCounter() + "times"));



    }

    private static void clientsWhoRangTheMost(List<Call> callList, int N) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (Call call : callList) {
            map.computeIfAbsent(call.getCallerID(), k -> new ArrayList<>()).add(call.getReceiverID());
        }

        List<Client> clientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e->{
                            Client client = new Client(e);
                            client.setCallCounter(map.get(e).size());
                            return client;
                        }).toList()
        );

        clientList.sort((e1, e2) -> e2.getCallCounter() - e1.getCallCounter());
        clientList.stream().limit(N).forEach(client -> System.out.println(client.getCallerID() + " was ringing " + client.getCallCounter() + " times"));

    }

    private static void clientsWhoGotTheMostCalls(List<Call> callList, int N) {

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (Call call : callList) {
            map.computeIfAbsent(call.getReceiverID(), k -> new ArrayList<>()).add(call.getReceiverID());
        }

        List<SecondClient> secondClientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e-> {
                            SecondClient secondClient = new SecondClient(e);
                            secondClient.setCallCounter(map.get(e).size());
                            return secondClient;
                        }).toList()
        );

        secondClientList.sort((e1, e2) -> e2.getCallCounter() - e1.getCallCounter());
        secondClientList.stream().limit(N).forEach(secondClient -> System.out.println(secondClient.getReceiverID() + " get called " + secondClient.getCallCounter() + "times"));


    }

    private static void clientsWhoWereCallingLeastTimes(List<Call> callList, int N) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (Call call : callList) {
            map.computeIfAbsent(call.getCallerID(), k -> new ArrayList<>()).add(call.getReceiverID());
        }

        List<Client> clientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e->{
                            Client client = new Client(e);
                            client.setCallCounter(map.get(e).size());
                            return client;
                        }).toList()
        );

        clientList.sort(Comparator.comparingInt(Client::getCallCounter));
        clientList.stream().limit(N).forEach(client -> System.out.println(client.getCallerID() + " was ringing " + client.getCallCounter() + " times"));

    }

    private static void clientsWhoGotTheFewestCalls(List<Call> callList, int N) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (Call call : callList) {
            map.computeIfAbsent(call.getReceiverID(), k -> new ArrayList<>()).add(call.getReceiverID());
        }

        List<SecondClient> clientList = new ArrayList<>(
                map.keySet().stream()
                        .map(e->{
                            SecondClient client = new SecondClient(e);
                            client.setCallCounter(map.get(e).size());
                            return client;
                        }).toList()
        );

        clientList.sort(Comparator.comparingInt(SecondClient::getCallCounter));
        clientList.stream().limit(N).forEach(client -> System.out.println(client.getReceiverID() + " was ringing " + client.getCallCounter() + " times"));

    }
    private static void fullInformationAboutClient(List<Call> callList, int N) {
        HashMap<Integer, Integer> outgoingCallCount = new HashMap<>();
        HashMap<Integer, Integer> incomingCallCount = new HashMap<>();
        HashMap<Integer, Integer> outgoingCallDuration = new HashMap<>();

        for (Call call : callList) {
            outgoingCallCount.put(call.getCallerID(), outgoingCallCount.getOrDefault(call.getCallerID(), 0) + 1);
            incomingCallCount.put(call.getReceiverID(), incomingCallCount.getOrDefault(call.getReceiverID(), 0) + 1);
            outgoingCallDuration.put(call.getCallerID(), outgoingCallDuration.getOrDefault(call.getCallerID(), 0) + call.getCallDuration());
        }

        for (int clientId = 1; clientId <= N; clientId++) {
            System.out.println("Client ID: " + clientId);
            System.out.println("Outgoing Calls: " + outgoingCallCount.getOrDefault(clientId, 0));
            System.out.println("Incoming Calls: " + incomingCallCount.getOrDefault(clientId, 0));
            System.out.println("Outgoing Call Duration: " + outgoingCallDuration.getOrDefault(clientId, 0) + " seconds");
            System.out.println("Charge to Pay: " + calculateCharge(outgoingCallDuration.getOrDefault(clientId, 0)));
            System.out.println();
        }
    }

    private static double calculateCharge(int callDuration) {
        double chargePerSecond = 0.002;
        return callDuration * chargePerSecond;
    }


    public static void main(String[] args) {

        int K = 500; //liczba klientów
        int N = 200000; //liczba rozmów

        int howManyToPrint = 7;

        Random random = new Random(123);

        String filePath = "Calls.txt";

        File file = new File(filePath);

        try {
            Writer writer = new FileWriter(file);

            for (int i = 0; i < N;i++) {
                int caller = random.nextInt(K) + 1;
                int receiver = random.nextInt(K - 1) + 1;
                if (receiver >= caller) {
                    receiver++;
                }

                double duration = Math.max(0, random.nextGaussian() * 120 + 180);

                writer.write(caller + " " + receiver + " " + (int) duration + "\n");
            }

                writer.close();
        }
        catch (IOException e) {
            System.err.println("File does not exist or it cannot be read");
        }


        List<Call> allCalls;

        try {
            Stream<String> streamOfStrings = Files.lines(Paths.get(filePath));
            allCalls = streamOfStrings
                    .map(word -> word.split("\\s"))
                    .map(line -> new Call(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])))
                    .toList();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("List " + howManyToPrint + " clients that calls were the longest:");
        mostTimeCalling( allCalls, howManyToPrint);
        System.out.println();

        System.out.println("List of " + howManyToPrint + " clients that get called for the longest time:");
        mostTimeGettingCalls(allCalls, howManyToPrint);
        System.out.println();

        System.out.println("List of " + howManyToPrint + " unique clients that were ringing the most:");
        clientsWhoRangTheMostToOtherClients(allCalls, howManyToPrint);
        System.out.println();

        System.out.println("List of " + howManyToPrint + " unique clients that were getting calls the most:");
        clientsWhoWereGettingCallsTheMostFromOtherClients(allCalls, howManyToPrint);

        System.out.println("List of " + howManyToPrint + " clients who rang the most:");
        clientsWhoRangTheMost(allCalls, howManyToPrint);
        System.out.println();

        System.out.println("List of " + howManyToPrint + " clients who get called tho most:");
        clientsWhoGotTheMostCalls(allCalls, howManyToPrint);
        System.out.println();

        System.out.println("List of " + howManyToPrint + " clients who rang the least:");
        clientsWhoWereCallingLeastTimes(allCalls, howManyToPrint);
        System.out.println();

        System.out.println("List of " + howManyToPrint + " clients who received the fewest calls");
        clientsWhoGotTheFewestCalls(allCalls, howManyToPrint);
        System.out.println();

        System.out.println("Full information about 7 clients:");
        fullInformationAboutClient(allCalls, howManyToPrint);


    }


    private static class Call {
        int callerID;
        int receiverID;
        int callDuration;

        int callCounter;

        public int getCallCounter() {
            return callCounter;
        }

        @Override
        public String toString() {
            return "Call{" +
                    "callerID=" + callerID +
                    ", receiverID=" + receiverID +
                    ", callDuration=" + callDuration +
                    ", callCounter=" + callCounter +
                    '}';
        }

        public int getCallerID() {
            return callerID;
        }

        public int getReceiverID() {
            return receiverID;
        }

        public int getCallDuration() {
            return callDuration;
        }

        public Call(int callerID, int reciverID, int callDuration) {
            this.callerID = callerID;
            this.receiverID = reciverID;
            this.callDuration = callDuration;
        }
    }

    private static class Client {
        int callDuration;
        int callerID;
        int callCounter;

        public void setCallCounter(int callCounter) {
            this.callCounter = callCounter;
        }

        public int getCallCounter() {
            return callCounter;
        }

        public Client(int callerID) {
            this.callerID = callerID;
        }

        public int getCallerID() {
            return callerID;
        }

        public void setCallDuration(int callDuration) {
            this.callDuration = callDuration;
        }

        public int getCallDuration() {
            return callDuration;
        }

    }
    private static class SecondClient {
        int callDuration;

        int callCounter;
        int receiverID;

        public int getCallCounter() {
            return callCounter;
        }

        public void setCallCounter(int callCounter) {
            this.callCounter = callCounter;
        }

        public int getReceiverID() {
            return receiverID;
        }

        public SecondClient(int receiverID) {
            this.receiverID = receiverID;
        }

        public void setCallDuration(int callDuration) {
            this.callDuration = callDuration;
        }

        public int getCallDuration() {
            return callDuration;
        }

    }
}
