import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConversationService {

  private apiUrl = 'http://localhost:3001/api/conversation';

  constructor(private http: HttpClient) { }

  public getAllConversations(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/all/${userId}`, {withCredentials: true} );
  }

  public getConversation(conversationId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${conversationId}`, {withCredentials: true});
  }

  public startConversation(userId: number): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/create/${userId}`, {}, { withCredentials: true });
  }
}
