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
  private messagesSubject: BehaviorSubject<MessageInterface[]> = new BehaviorSubject<MessageInterface[]>([]);

  constructor() {
    this.socket = io('http://127.0.0.25:8085');

    this.socket.on('message', (message: MessageInterface) => {
      const currentMessages = this.messagesSubject.getValue();
      this.messagesSubject.next([...currentMessages, message]);
    });

    this.socket.on('connect', () => {
      console.log('Connected to the server');
    });
  }

  public getMessages(): Observable<MessageInterface[]> {
    return this.messagesSubject.asObservable();
  }

  public joinConversation(conversationId: number): void {
    this.socket.emit('join', conversationId);
    this.socket.emit('loadMessages', conversationId);
    console.log('inside join')
  }

  public sendMessage(conversationId: number, content: string, user: string): void {
    const message: MessageRequestInterface = {
      message : content,
      conversationId,
      sender: user
    };
    this.socket.emit('sendMessage', message);
    console.log('did send message')
  }

  public leaveConversation(conversationId: number): void {
    this.socket.emit('leave', conversationId);
    console.log('inside leave')
  }
}
