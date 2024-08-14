import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConversationService {

  private apiUrl = 'http://localhost:8080/api/conversation';

  constructor(private http: HttpClient) { }

  public getAllConversations(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/all/${userId}`);
  }

  public getConversation(conversationId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${conversationId}`);
  }
}
