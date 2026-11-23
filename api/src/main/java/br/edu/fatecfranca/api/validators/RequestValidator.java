package br.edu.fatecfranca.api.validators;

public abstract class RequestValidator<T> {

   private final RequestValidator<T> next;

   protected RequestValidator(RequestValidator<T> next) {
       this.next = next;
   }

   public final void validate(T request, Long entityId) {
       check(request, entityId);

       if (next != null) {
           next.validate(request, entityId);
       }
   }

   protected abstract void check(T request, Long entityId);
}
