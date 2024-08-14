import { Component, OnInit } from '@angular/core';
import { WebSocketService } from '../../../services/web-socket.service';
import { ConversationService } from '../../../services/conversation.service';
import { LocalStorageService } from '../../../storage/local-storage.service';
import { ConversationInterface } from '../../../interfaces/conversation.interface';

@Component({
  selector: 'app-conversations',
  templateUrl: './conversations.component.html',
  styleUrls: ['./conversations.component.scss']
})
export class ConversationsComponent implements OnInit {

  conversations: ConversationInterface[] = [];
  selectedConversation: any;
  messages: any[] = [];

  constructor(
    private conversationService: ConversationService,
    private webSocketService: WebSocketService,
    private localStorage: LocalStorageService,
  ) { }

  ngOnInit(): void {
    const userId: number = this.localStorage.getItem('id');
    this.loadConversations(userId);

    this.webSocketService.getMessages().subscribe(messages => {
      this.messages = messages;
    });
  }

  loadConversations(userId: number): void {
    this.conversationService.getAllConversations(userId).subscribe(conversations => {
      this.conversations = conversations;
    });
  }

  selectConversation(conversation: ConversationInterface): void {
    if (this.selectedConversation) {
      this.webSocketService.leaveConversation(this.selectedConversation.id);
    }

    this.selectedConversation = conversation;
    this.webSocketService.joinConversation(conversation.id);
  }

  getOtherUser(conversation: ConversationInterface): string {
    const currentUser = this.localStorage.getItem('userName');
    return conversation.users.find(user => user !== currentUser) || 'Unknown';
  }
}
