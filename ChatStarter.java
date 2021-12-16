package com.company.client;




import com.company.client.comunication.ClientCommunication;
import com.company.client.gui.ChatFrame;

import java.util.function.Consumer;


public class ChatStarter {

    private final ChatFrame frame;
    private final ClientCommunication communication;

    public ChatStarter() {

        communication = new ClientCommunication();
        LogsFiles logsFiles = new LogsFiles();

        Consumer <String> outboxMessageConsumer = communication::sendMessage;


        frame = new ChatFrame(outboxMessageConsumer);
           new Thread(()-> {
               while (true) {
                   String inboxMessage = communication.receiveMessage();
                   frame.getInboundMessageConsumer().accept(inboxMessage);

                   logsFiles.setFile(communication.receiveMessage());

               }
           }).start();

    }
}
