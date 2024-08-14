import { Component, Input } from '@angular/core';
import { WebSocketService } from '../../../services/web-socket.service';
import { LocalStorageService } from '../../../storage/local-storage.service';
import { MessageInterface } from '../../../interfaces/message.interface';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent {
  @Input() messages: MessageInterface[] = [];
  @Input() conversationId: number = 0
  newMessage: string = '';

  constructor(
    private webSocketService: WebSocketService,
    protected localStorage: LocalStorageService
  ) {}

  sendMessage(): void {
    if (this.newMessage.trim()) {
      const user = this.localStorage.getItem('userName');
      this.webSocketService.sendMessage(this.conversationId, this.newMessage, user);
      this.newMessage = '';
    }
  }
}

