import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ConversationsComponent} from "./conversations/conversations.component";
import {MessagesComponent} from "./messages/messages.component";

const routes : Routes = [
  { title: 'Conversation', path: 'conversations', component : ConversationsComponent },
  { title: 'Message', path: 'message', component : MessagesComponent },
  { path: '', redirectTo: 'conversations', pathMatch: 'full' }

]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class ChatRoutingModule { }
