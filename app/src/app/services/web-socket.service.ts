import { Injectable } from '@angular/core';
import { io, Socket } from 'socket.io-client';
import { BehaviorSubject, Observable } from 'rxjs';
import { MessageInterface } from '../interfaces/message.interface';
import {MessageRequestInterface} from "../interfaces/messageRequest.interface";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private socket: Socket;
  private messagesMap: { [conversationId: number]: MessageInterface[] } = {};
  private currentConversationId: number | null = null;
  private messagesSubject: BehaviorSubject<MessageInterface[]> = new BehaviorSubject<MessageInterface[]>([]);

  constructor() {
    this.socket = io('http://127.0.0.25:8085');

    this.socket.on('message', (message: MessageInterface) => {
      const conversationId = message.conversationId;

      if (!conversationId) {
        console.error('Message received without a conversationId:', message);
        return;
      }

      if (!this.messagesMap[conversationId]) {
        this.messagesMap[conversationId] = [];
      }

      this.messagesMap[conversationId].push(message);

      if (this.currentConversationId === conversationId) {
        this.messagesSubject.next([...this.messagesMap[conversationId]]);
      }
    });

    this.socket.on('connect', () => {
      console.log('Connected to the server');
    });
  }

  public getMessages(): Observable<MessageInterface[]> {
    return this.messagesSubject.asObservable();
  }

  public joinConversation(conversationId: number): void {
    this.currentConversationId = conversationId;
    this.messagesMap[conversationId] = [];
    this.messagesSubject.next([]);
    this.socket.emit('join', conversationId);
    this.socket.emit('loadMessages', conversationId);
  }

  public sendMessage(conversationId: number, content: string, user: string): void {
    const message: MessageRequestInterface = {
      message: content,
      conversationId,
      sender: user
    };
    this.socket.emit('sendMessage', message);
    console.log('did send message');
  }

  public leaveConversation(conversationId: number): void {
    this.socket.emit('leave', conversationId);
    console.log('inside leave');
    if (this.currentConversationId === conversationId) {
      this.currentConversationId = null;
    }
  }
}
