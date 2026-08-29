package com.dio.budgetai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SpeechService {

    private final OpenAiAudioTranscriptionModel transcriptionModel;
    private final OpenAiAudioSpeechModel speechModel;
    private final ChatClient chatClient;

    public SpeechService(OpenAiAudioTranscriptionModel transcriptionModel,
                         OpenAiAudioSpeechModel speechModel,
                         ChatClient.Builder chatClientBuilder) {
        this.transcriptionModel = transcriptionModel;
        this.speechModel = speechModel;
        this.chatClient = chatClientBuilder.build();
    }

    public byte[] processAudioCommand(Resource audioFile) {
        String userText = transcriptionModel.call(audioFile);

        String aiResponse = chatClient.prompt()
                .user(userText)
                .functions("checkBudgetLimit", "registerTransaction")
                .call()
                .content();

        return speechModel.call(aiResponse);
    }
}
