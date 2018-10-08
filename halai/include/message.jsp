<ww:if test="actionErrors.size>0">  
  <div id="mess">
       <ww:iterator value="actionErrors">
          <ww:property/>
       </ww:iterator>
       <ww:iterator value="actionMessages">
          <ww:property/>
       </ww:iterator>
   </div>
</ww:if>