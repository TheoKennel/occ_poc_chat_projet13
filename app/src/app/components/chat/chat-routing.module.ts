import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ConversationsComponent} from "./conversations/conversations.component";
import {MessageComponent} from "./messages/message.component";

const routes : Routes = [
  { title: 'Conversation', path: 'conversations', component : ConversationsComponent },
  { title: 'Message', path: 'message', component : MessageComponent },
  { path: '', redirectTo: 'conversations', pathMatch: 'full' }

]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class ChatRoutingModule { }
