import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FlexLayoutModule} from "@angular/flex-layout";
import {ConversationsComponent} from "./conversations/conversations.component";
import {ChatRoutingModule} from "./chat-routing.module";
import {MessageComponent} from "./messages/message.component";

@NgModule({
  declarations: [
    ConversationsComponent,
    MessageComponent,
  ],
  imports: [
    ChatRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
  ]
})

export class ChatModule {}
